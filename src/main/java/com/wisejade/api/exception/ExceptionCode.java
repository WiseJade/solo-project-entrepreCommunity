package com.wisejade.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    COMPANYNAME_EXISTS(409, "Company name exists"),
    CITY_NOT_FOUND(404, "CityCode not found");

    @Getter
    private int status;

    @Getter
    private String message;
}
