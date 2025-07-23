package com.devmeeple.spring.basic;

import com.devmeeple.spring.basic.discount.DiscountPolicy;
import com.devmeeple.spring.basic.discount.FixDiscountPolicy;
import com.devmeeple.spring.basic.member.MemberRepository;
import com.devmeeple.spring.basic.member.MemberService;
import com.devmeeple.spring.basic.member.MemberServiceImpl;
import com.devmeeple.spring.basic.member.MemoryMemberRepository;
import com.devmeeple.spring.basic.order.OrderService;
import com.devmeeple.spring.basic.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
