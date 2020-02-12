package com.kys.r2dbc.user.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author kody.kim
 * @since 12/02/2020
 */
public interface PersonHandler {

    Mono<ServerResponse> add(ServerRequest request);

    Mono<ServerResponse> search(ServerRequest request);
}
