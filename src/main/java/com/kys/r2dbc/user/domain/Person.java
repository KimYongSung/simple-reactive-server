package com.kys.r2dbc.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@NoArgsConstructor
@Getter
@Table(value = "PERSON")
@EqualsAndHashCode
public class Person {

    @Id
    private Long id;

    private String name;

    private String address;

    private Integer age;

    @Builder
    @PersistenceConstructor
    public Person(Long id, String name, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
