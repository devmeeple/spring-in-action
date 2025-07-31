package com.devmeeple.spring.basic;

import com.devmeeple.spring.basic.discount.DiscountPolicy;
import com.devmeeple.spring.basic.discount.RateDiscountPolicy;
import com.devmeeple.spring.basic.member.MemberRepository;
import com.devmeeple.spring.basic.member.MemberService;
import com.devmeeple.spring.basic.member.MemberServiceImpl;
import com.devmeeple.spring.basic.member.MemoryMemberRepository;
import com.devmeeple.spring.basic.order.OrderService;
import com.devmeeple.spring.basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
