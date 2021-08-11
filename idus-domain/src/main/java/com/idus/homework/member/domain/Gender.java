package com.idus.homework.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MAN("MAN"),
    WOMAN("WOMAN"),
    UNKNOWN("UNKNOWN");

    private String value;
}