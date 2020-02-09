package com.kys.r2dbc.user.service;

import com.kys.r2dbc.user.domain.Person;
import com.kys.r2dbc.user.domain.repository.PersonRepository;
import com.kys.r2dbc.user.model.PersonDTO;
import com.kys.r2dbc.user.model.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Mono<PersonResponse> personSave(PersonDTO dto){

        Person person = dto.toEntity();

        return personRepository.save(person)
                               .map(personMono -> PersonResponse.ok());
    }
}
