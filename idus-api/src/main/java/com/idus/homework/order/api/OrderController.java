package com.idus.homework.order.api;

import com.idus.homework.common.ApiResponse;
import com.idus.homework.common.exception.MemberNotFoundException;
import com.idus.homework.member.representation.MemberDetailResponse;
import com.idus.homework.order.app.OrderAppService;
import com.idus.homework.order.representation.OrderListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("주문 관련")
@RequestMapping("/v1/order")
@RestController
public class OrderController {

    private final OrderAppService orderAppService;

    public OrderController(OrderAppService orderAppService) {
        this.orderAppService = orderAppService;
    }

    @ApiOperation(value = "단일 회원의 주문 목록 조회 API")
    @GetMapping("/member/{id}")
    public ApiResponse<OrderListResponse> findById(@PathVariable(name = "id") long id){
        return ApiResponse.ok(orderAppService.findAllByOrdererId(id));
    }

}