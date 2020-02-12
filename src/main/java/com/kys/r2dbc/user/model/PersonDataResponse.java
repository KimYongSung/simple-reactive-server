package com.kys.r2dbc.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kys.r2dbc.user.constants.ErrorCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author kody.kim
 * @since 12/02/2020
 */
@Getter
@ToString(callSuper = true)
public class PersonDataResponse<T> extends PersonResponse {

    @JsonProperty(value = "data")
    private final T data;

    public PersonDataResponse(ErrorCode errorCode, T data) {
        super(errorCode);
        this.data = data;
    }

    public static <T> PersonDataResponse ok(T data){
        return new PersonDataResponse(ErrorCode.CD_0000, data);
    }

}
