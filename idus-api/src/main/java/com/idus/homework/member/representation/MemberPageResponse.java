package com.idus.homework.member.representation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberPageResponse {

    private long totalElements;

    private long totalPages;

    private List<MemberOrderResponse> members;

    @Builder
    public MemberPageResponse(long totalElements, long totalPages, List<MemberOrderResponse> members) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.members = members;
    }

}