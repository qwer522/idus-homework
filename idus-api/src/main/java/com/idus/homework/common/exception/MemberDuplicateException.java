package com.idus.homework.common.exception;

public class MemberDuplicateException extends Exception {
    public MemberDuplicateException() {
        super("이메일이 중복됩니다.");
    }
}
