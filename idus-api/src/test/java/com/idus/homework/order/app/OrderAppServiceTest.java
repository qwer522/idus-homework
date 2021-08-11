package com.idus.homework.order.app;

import static org.junit.jupiter.api.Assertions.*;

import com.idus.homework.order.representation.OrderListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@SpringBootTest
@Transactional
class OrderAppServiceTest {

    @Autowired
    private OrderAppService orderAppService;

    @Test
    void 주문이_없는_회원_아이디로_조회를_하면_빈_값을_반환해야_한다() {
        // given
        Long id = Long.valueOf(1000);

        // when
        OrderListResponse response = orderAppService.findAllByOrdererId(id);

        // then
        assertTrue(ObjectUtils.isEmpty(response.getOrders()));
    }

    @Test
    void 주문이_있는_회원_아이디로_조회를_하면_데이터를_반환해야_한다() {
        // given
        Long id = Long.valueOf(1);

        // when
        OrderListResponse response = orderAppService.findAllByOrdererId(id);

        // then
        assertFalse(ObjectUtils.isEmpty(response.getOrders()));
    }
}