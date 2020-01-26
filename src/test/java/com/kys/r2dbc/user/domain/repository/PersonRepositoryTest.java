package com.kys.r2dbc.user.domain.repository;

import com.kys.r2dbc.user.domain.Person;
import com.kys.r2dbc.user.model.PersonDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("person 저장 테스트")
    public void personSaveTest(){

        // given
        PersonDTO dto = PersonDTO.builder()
                                 .address("서울시 강북구 수유동")
                                 .age(32)
                                 .name("kys0213")
                                 .build();

        // when
        Mono<Person> personMono = personRepository.save(dto.toEntity());

        // then
        Mono<Person> personMono1 = personRepository.findById(1l);

        Person person = personMono.block();
        Person findPerson = personMono1.block();

        assertThat(person).isEqualTo(findPerson);

        /*personMono.subscribe(person -> {
            Mono<Person> personMono1 = personRepository.findById(1l);
        });*/
    }
}
