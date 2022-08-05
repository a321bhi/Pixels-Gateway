package com.pixels.pixelsgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class PixelsGatewayApplication {
	@Value("${application.microservice.user}")
	private String userServiceUrl; 
	@Value("${application.microservice.story}")
	private String storyServiceUrl;
	@Value("${application.microservice.chat}")
	private String chatServiceUrl; 
	@Value("${application.microservice.media}")
	private String mediaServiceUrl;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/user/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(userServiceUrl))
				.route(r -> r.path("/story/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(storyServiceUrl))
				.route(r -> r.path("/chat/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(chatServiceUrl))
				.route(r -> r.path("/service/**")
						//.filters(f -> f.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8081"))
						.uri(mediaServiceUrl))
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
