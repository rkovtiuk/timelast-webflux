package com.rkovtiuk.timelast.route;

import com.rkovtiuk.timelast.handler.TweetHandler;
import com.rkovtiuk.timelast.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.rkovtiuk.timelast.common.utils.RoutesConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return RouterFunctions
                .route(GET(USER).and(accept(APPLICATION_JSON)), handler::one);
    }

    @Bean
    public RouterFunction<ServerResponse> tweetRoutes(TweetHandler handler) {
        return RouterFunctions
                .route(GET(ALL_TWEETS).and(accept(APPLICATION_JSON)), handler::all)
                .andRoute(GET(ONE_TWEET).and(accept(APPLICATION_JSON)), handler::one)
                .andRoute(POST(ADD_TWEET).and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))), handler::save);
    }

}
