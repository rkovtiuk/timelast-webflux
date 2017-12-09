package com.rkovtiuk.timelast.route;

import com.rkovtiuk.timelast.handler.TweetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class TweetRoute {

    @Bean
    public RouterFunction<ServerResponse> route(TweetHandler handler) {
        return RouterFunctions
                .route(GET("/quotes").and(accept(APPLICATION_JSON)), handler::all)
                .andRoute(GET("/quotes/{id}").and(accept(APPLICATION_JSON)), handler::one)
                .andRoute(POST("/quotes").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))), handler::save);
    }

}
