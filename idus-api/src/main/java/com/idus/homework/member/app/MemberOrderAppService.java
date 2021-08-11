package com.idus.homework.member.app;

import com.idus.homework.member.domain.Member;
import com.idus.homework.member.infra.service.MemberQueryService;
import com.idus.homework.member.representation.MemberOrderResponse;
import com.idus.homework.member.representation.MemberPageResponse;
import com.idus.homework.member.representative.MemberSearchCondition;
import com.idus.homework.order.domain.Order;
import com.idus.homework.order.infra.service.OrderQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberOrderAppService {

    private final MemberQueryService memberQueryService;
    private final OrderQueryService orderQueryService;

    public MemberOrderAppService(MemberQueryService memberQueryService, OrderQueryService orderQueryService) {
        this.memberQueryService = memberQueryService;
        this.orderQueryService = orderQueryService;
    }

    @Transactional(readOnly = true)
    public MemberPageResponse findAllByCondition(MemberSearchCondition memberSearchCondition, Pageable pageable) {
        Page<Member> memberPage = memberQueryService.findAllByCondition(memberSearchCondition,
            pageable);

        List<MemberOrderResponse> memberOrderResponses = getOrderByOrdererId(memberPage);

        return dataToMemberPageResponse(memberPage, memberOrderResponses);
    }

    private MemberPageResponse dataToMemberPageResponse(Page<Member> memberPage, List<MemberOrderResponse> memberOrderResponses) {
        return MemberPageResponse.builder()
            .totalElements(memberPage.getTotalElements())
            .totalPages(memberPage.getTotalPages())
            .members(memberOrderResponses)
            .build();
    }

    private List<MemberOrderResponse> getOrderByOrdererId(Page<Member> memberPage) {
        return memberPage.getContent().stream()
            .map(e -> {
                Optional<Order> order = orderQueryService.findLastOrderByOrdererId(e.getId());
                if (order.isPresent()) {
                    return MemberOrderResponse.of(e, order.get());
                }else {
                    return MemberOrderResponse.from(e);
                }
            }).collect(Collectors.toList());
    }

}
