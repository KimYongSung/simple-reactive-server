package com.kys.r2dbc.user.model;

import com.kys.r2dbc.user.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonDTO {

    private String name;

    private String address;

    private Integer age;

    public Person toEntity(){
        return Person.builder()
                .name(name)
                .address(address)
                .age(age)
                .build();
    }
}
