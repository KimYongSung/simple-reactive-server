package com.kys.r2dbc.test.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * HelloWorld 핸들러
 */
@Component
public class HelloWorldHandler {

    /**
     * hello world
     * @param request
     * @return
     */
    public Mono<ServerResponse> helloWorld(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("hello world"), String.class)
                ;
    }


}
