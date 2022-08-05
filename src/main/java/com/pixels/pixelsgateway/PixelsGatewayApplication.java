package com.pixels.pixelsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelsGatewayApplication {
	private String userServiceUrl = "http://pixels-story-microservice";
	private String storyServiceUrl = "http://pixels-chat-microservice";
	private String chatServiceUrl = "http://pixels-media-microservice";
	private String mediaServiceUrl = "http://pixels-user-microservice";

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
