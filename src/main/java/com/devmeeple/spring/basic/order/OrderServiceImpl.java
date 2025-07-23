package com.devmeeple.spring.basic.order;

import com.devmeeple.spring.basic.discount.DiscountPolicy;
import com.devmeeple.spring.basic.member.Member;
import com.devmeeple.spring.basic.member.MemberRepository;
import com.devmeeple.spring.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
