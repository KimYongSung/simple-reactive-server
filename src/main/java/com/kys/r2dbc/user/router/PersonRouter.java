package com.kys.r2dbc.user.router;

import com.kys.r2dbc.user.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<?> routes(PersonHandler handler){
        return RouterFunctions.route(GET("/person"), handler::search)
                              .andRoute(POST("/person"), handler::add)
                ;
    }
}
