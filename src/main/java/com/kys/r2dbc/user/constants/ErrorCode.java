package com.kys.r2dbc.user.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    CD_0000("0000", "성공")
    , CD_0001("0001", "파라미터 유효성 검사 에러")
    ;

    private final String code;

    private final String message;
}
