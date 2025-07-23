package com.devmeeple.spring.basic;

import com.devmeeple.spring.basic.member.Grade;
import com.devmeeple.spring.basic.member.Member;
import com.devmeeple.spring.basic.member.MemberService;
import com.devmeeple.spring.basic.order.Order;
import com.devmeeple.spring.basic.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
