package com.kys.r2dbc.user.domain.repository;

import com.kys.r2dbc.user.domain.Person;
import com.kys.r2dbc.user.model.PersonDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private PersonRepositoryTestVerifier verifier = new PersonRepositoryTestVerifier();

    @Test
    @DisplayName("person 저장 테스트")
    public void personSaveTest(){

        // given
        PersonDTO dto = makeParameter();

        // when
        Person person = personRepository.save(dto.toEntity()).block();

        // then
        Person findPerson = personRepository.findById(person.getId()).block();

        verifier.verifyExistAndEqualsByPerson(person, findPerson);
    }

    @Test
    @DisplayName("person 조회 테스트")
    public void personFindTest(){

        // given
        Person person = givenPersonInsert();

        // when
        Person findPerson = personRepository.findByName(person.getName()).block();

        // then
        verifier.verifyExistAndEqualsByPerson(person, findPerson);
    }

    private Person givenPersonInsert() {
        PersonDTO dto = makeParameter();

        Mono<Person> personMono = personRepository.save(dto.toEntity());

        return personMono.block();
    }

    private PersonDTO makeParameter() {
        return PersonDTO.builder()
                .address("서울시 강북구 수유동")
                .age(32)
                .name("kys0213")
                .build();
    }
}