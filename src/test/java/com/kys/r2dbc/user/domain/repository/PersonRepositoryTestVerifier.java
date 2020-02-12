package com.kys.r2dbc.user.domain.repository;

import com.kys.r2dbc.user.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kody.kim
 * @since 12/02/2020
 */
public class PersonRepositoryTestVerifier {

    /**
     * Person 검증 로직
     * @param save
     * @param find
     */
    public void verifyExistAndEqualsByPerson(Person save, Person find) {
        assertThat(find).isNotNull();
        assertThat(save).isEqualTo(find);
    }
}
