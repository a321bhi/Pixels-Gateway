package com.pixels.pixelsgateway.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.web.server.ServerWebExchange;

import com.pixels.pixelsgateway.exception.JwtTokenMissingException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

	@Autowired
	private final RouterValidator routerValidator;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtConfig jwtConfig;

	public AuthenticationFilter(RouterValidator routerValidator, JwtTokenUtil jwtTokenUtil, JwtConfig config) {
		this.routerValidator = routerValidator;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtConfig = config;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String username="";
		if (routerValidator.isSecured.test(exchange.getRequest())) {
			if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				ErrorResponseDTO error = new ErrorResponseDTO(new Date(), HttpStatus.UNAUTHORIZED.value(),
						"UNAUTHORIZED - token is missing",  exchange.getRequest().getURI().toString());
				ServerHttpResponse response = exchange.getResponse();
				byte[] bytes = SerializationUtils.serialize(error);

				DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				response.writeWith(Flux.just(buffer));
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}

			String authHeader = Objects
					.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
			try {
				jwtTokenUtil.validateToken(authHeader);
				username = jwtTokenUtil.getUsername(authHeader);
			} catch (Exception ex) {
				List<String> details = new ArrayList<>();
				details.add(ex.getLocalizedMessage());
				ErrorResponseDTO error = new ErrorResponseDTO(new Date(), HttpStatus.UNAUTHORIZED.value(),
						"UNAUTHORIZED", details, exchange.getRequest().getURI().toString());
				ServerHttpResponse response = exchange.getResponse();

				byte[] bytes = SerializationUtils.serialize(error);

				DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				response.writeWith(Flux.just(buffer));
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}
		}
		exchange.mutate().request(
                exchange.getRequest().mutate()
                        .header("username",username)
                        .build());
		return chain.filter(exchange);
	}
}