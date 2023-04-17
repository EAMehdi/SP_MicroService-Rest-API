package mservice.gateway_module;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book_route", r -> r.path("/books/**")
                        .uri("http://localhost:8002"))
                .route("reader_route", r -> r.path("/readers/**")
                        .uri("http://localhost:8001"))
                .route("lending_route", r -> r.path("/lendings/**")
                        .uri("http://localhost:8003"))
                .build();
    }
}
