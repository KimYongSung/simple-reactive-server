package com.kys.r2dbc.user.model;

import com.kys.r2dbc.user.constants.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonResponse {

    private final String code;

    private final String message;

    public PersonResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static PersonResponse ok(){
        return new PersonResponse(ErrorCode.CD_0000);
    }
}
