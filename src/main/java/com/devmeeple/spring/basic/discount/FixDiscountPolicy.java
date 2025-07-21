package com.devmeeple.spring.basic.discount;

import com.devmeeple.spring.basic.member.Grade;
import com.devmeeple.spring.basic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
