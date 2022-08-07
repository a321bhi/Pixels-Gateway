package com.pixels.pixelsgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelsGatewayApplication {
	@Value("${application.serviceurl.userservice}")
	private String userServiceUrl;
	@Value("${application.serviceurl.storyservice}")
	private String storyServiceUrl;
	@Value("${application.serviceurl.chatservice}")
	private String chatServiceUrl;
	@Value("${application.serviceurl.mediaservice}")
	private String mediaServiceUrl;

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
