package com.devmeeple.spring.basic;

import com.devmeeple.spring.basic.member.Grade;
import com.devmeeple.spring.basic.member.Member;
import com.devmeeple.spring.basic.member.MemberService;
import com.devmeeple.spring.basic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
