package com.idus.homework.member.representation;

import com.idus.homework.member.domain.Gender;
import com.idus.homework.member.domain.Member;
import com.idus.homework.order.domain.Order;
import com.idus.homework.order.representation.OrderResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberOrderResponse {
    @ApiModelProperty("회원ID")
    private Long id;
    @ApiModelProperty("이메일")
    private String email;
    @ApiModelProperty("이름")
    private String name;
    @ApiModelProperty("전화번호")
    private String phone;
    @ApiModelProperty("별칭")
    private String alias;
    @ApiModelProperty("성별")
    private Gender gender;
    @ApiModelProperty("마지막주문정보")
    private OrderResponse lastOrder;

    @Builder
    public MemberOrderResponse(Long id, String email, String name, String phone, String alias,
                               Gender gender, OrderResponse lastOrder) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.alias = alias;
        this.gender = gender;
        this.lastOrder = lastOrder;
    }

    public static MemberOrderResponse from(Member member) {
        return MemberOrderResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .phone(member.getPhone())
            .alias(member.getAlias())
            .gender(member.getGender())
            .lastOrder(OrderResponse.empty())
            .build();
    }

    public static MemberOrderResponse of(Member member, Order order) {
        return MemberOrderResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .phone(member.getPhone())
            .alias(member.getAlias())
            .gender(member.getGender())
            .lastOrder(OrderResponse.from(order))
            .build();
    }
}
