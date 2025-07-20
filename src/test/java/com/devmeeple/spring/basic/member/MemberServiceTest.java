package com.devmeeple.spring.basic.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @DisplayName("회원가입에 성공한다.")
    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member result = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(result);
    }
}
