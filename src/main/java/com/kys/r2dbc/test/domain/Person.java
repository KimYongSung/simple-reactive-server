package com.kys.r2dbc.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@NoArgsConstructor
@Getter
@Table(value = "PERSON")
public class Person {

    @Id
    private Long id;

    private String name;

    private String address;

    private Integer age;

    @Builder
    public Person(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
