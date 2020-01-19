package com.kys.r2dbc.test.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;


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
        Mono<String> mono = request.bodyToMono(String.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("hello world"), String.class)
                ;
    }


}
