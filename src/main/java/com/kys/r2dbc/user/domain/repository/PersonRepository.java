package com.kys.r2dbc.user.domain.repository;

import com.kys.r2dbc.user.domain.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    @Query("SELECT * FROM person WHERE name = :name")
    Flux<Person> findByName(String name);
}
