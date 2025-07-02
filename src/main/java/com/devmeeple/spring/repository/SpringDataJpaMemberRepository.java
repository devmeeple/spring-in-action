package com.devmeeple.spring.repository;

import com.devmeeple.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //    JPQL SELECT m FROM Member m WHERE m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
