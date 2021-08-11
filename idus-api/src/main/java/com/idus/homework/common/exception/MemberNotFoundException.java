package com.idus.homework.common.exception;

public class MemberNotFoundException extends Exception {
    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.");
    }
}
