package beyondeyesight.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(r -> r.path("/ws-stomp/**")
//                .filters(f -> f.rewritePath("/ws-stomp/(?.*)", "/${remains}"))
                .uri("lb://FELLOWSHIPSERVICE/")
                .id("fellowshipservice"))
            .build();
    }
}
