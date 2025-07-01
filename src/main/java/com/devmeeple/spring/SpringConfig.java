package com.devmeeple.spring;

import com.devmeeple.spring.repository.MemberRepository;
import com.devmeeple.spring.repository.MemoryMemberRepository;
import com.devmeeple.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
