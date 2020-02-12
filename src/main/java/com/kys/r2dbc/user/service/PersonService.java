package com.kys.r2dbc.user.service;

import com.kys.r2dbc.user.model.PersonDTO;
import com.kys.r2dbc.user.model.PersonResponse;
import reactor.core.publisher.Mono;

public interface PersonService {

    /**
     * 고객정보 저장
     * @param dto
     * @return
     */
    Mono<PersonResponse> personSave(PersonDTO dto);

    /**
     * 고객정보 이름으로 조회
     * @param dto
     * @return
     */
    Mono<PersonResponse> findPersonByName(PersonDTO dto);
}
