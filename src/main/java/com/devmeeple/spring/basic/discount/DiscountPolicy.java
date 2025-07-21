package com.devmeeple.spring.basic.discount;

import com.devmeeple.spring.basic.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
