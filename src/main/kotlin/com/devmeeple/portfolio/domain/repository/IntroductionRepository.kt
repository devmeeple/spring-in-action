package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Introduction, Long> {

    // SELECT * FROM introduction WHERE is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Introduction>
}
