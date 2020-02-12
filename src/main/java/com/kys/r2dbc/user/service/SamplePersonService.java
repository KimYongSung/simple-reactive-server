package com.kys.r2dbc.user.service;

import com.kys.r2dbc.user.domain.repository.PersonRepository;
import com.kys.r2dbc.user.model.PersonDTO;
import com.kys.r2dbc.user.model.PersonDataResponse;
import com.kys.r2dbc.user.model.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SamplePersonService implements PersonService {

    private final PersonRepository personRepository;

    /**
     * 고객정보 저장
     * @param dto
     * @return
     */
    @Override
    public Mono<PersonResponse> personSave(PersonDTO dto){
        return personRepository.save(dto.toEntity())
                               .map(personMono -> PersonResponse.ok());
    }

    /**
     * 고객정보 이름으로 조회
     * @param dto
     * @return
     */
    @Override
    public Mono<PersonResponse> findPersonByName(PersonDTO dto){
        return personRepository.findByName(dto.getName())
                               .map(PersonDataResponse::ok);
    }
}
