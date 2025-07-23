package com.devmeeple.spring.basic;

import com.devmeeple.spring.basic.discount.FixDiscountPolicy;
import com.devmeeple.spring.basic.member.MemberService;
import com.devmeeple.spring.basic.member.MemberServiceImpl;
import com.devmeeple.spring.basic.member.MemoryMemberRepository;
import com.devmeeple.spring.basic.order.OrderService;
import com.devmeeple.spring.basic.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy()
        );
    }
}
