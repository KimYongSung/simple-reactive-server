package com.kys.r2dbc.user.handler;

import com.kys.r2dbc.user.model.PersonDTO;
import com.kys.r2dbc.user.model.PersonResponse;
import com.kys.r2dbc.user.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonHandler {

    private final PersonService personService;

    /**
     * person 추가
     * @param request
     * @return
     */
    public Mono<ServerResponse> add(ServerRequest request){
        return request.bodyToMono(PersonDTO.class)
                      .map(personService::personSave)
                      .flatMap(response -> ServerResponse.ok().body(response,PersonResponse.class));
    }

    /**
     * person 조회
     * @param request
     * @return
     */
    public Mono<ServerResponse> search(ServerRequest request){
        return request.bodyToMono(PersonDTO.class)
                      .map(personService::findPersonByName)
                      .flatMap(response -> ServerResponse.ok().body(response,PersonResponse.class));
    }
}
