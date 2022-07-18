package com.pixels.pixelsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelsGatewayApplication {

	private String userServiceUrl = "http://localhost:8090";
	private String feedServiceUrl = "http://localhost:8102";
	private String chatServiceUrl = "http://localhost:8103";

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/user/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(userServiceUrl))
				.route(r -> r.path("/feed/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(feedServiceUrl))
				.route(r -> r.path("/chat/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(chatServiceUrl))
				.build();
	}

//	@Bean
//	public RouterFunction<ServerResponse> testFunRouterFunction() {
//		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.path("/testfun"),
//				request -> ServerResponse.ok().body(BodyInserters.fromValue("hello")));
//		return route;
//	}

	public static void main(String[] args) {
		SpringApplication.run(PixelsGatewayApplication.class, args);
	}

}
