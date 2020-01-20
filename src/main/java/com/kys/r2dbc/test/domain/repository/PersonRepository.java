package com.kys.r2dbc.test.domain.repository;

import com.kys.r2dbc.test.domain.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
