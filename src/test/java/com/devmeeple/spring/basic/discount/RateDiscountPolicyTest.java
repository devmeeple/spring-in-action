package com.devmeeple.spring.basic.discount;

import com.devmeeple.spring.basic.member.Grade;
import com.devmeeple.spring.basic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    @Test
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int result = discountPolicy.discount(member, 10000);

        // then
        assertThat(result).isEqualTo(1000);
    }

    @DisplayName("VIP가 아니라면 할인이 적용되지 않아야 한다.")
    @Test
    void vip_x() {
        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        // when
        int result = discountPolicy.discount(member, 10000);

        // then
        assertThat(result).isEqualTo(0);
    }
}
