package com.pixels.pixelsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelsGatewayApplication {
	private String userServiceUrl = "http://localhost:8090";
	private String storyServiceUrl = "http://localhost:8102";
	private String chatServiceUrl = "http://localhost:8103";
	private String mediaServiceUrl = "http://localhost:8101";

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/user/**")
				.uri(userServiceUrl)).route(r -> r.path("/story/**")
						.uri(storyServiceUrl))
				.route(r -> r.path("/chat/**")
						.uri(chatServiceUrl))
				.route(r -> r.path("/service/**")
						.uri(mediaServiceUrl))
				.build();
	}


	public static void main(String[] args) {
		SpringApplication.run(PixelsGatewayApplication.class, args);
	}

}
