package com.kys.r2dbc.test.router;

import com.kys.r2dbc.test.handler.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class HelloWorldRouter {

    @Bean
    public RouterFunction<?> routes(HelloWorldHandler handler){
        return RouterFunctions.route(GET("/helloWorld"), handler::helloWorld);
    }

}
