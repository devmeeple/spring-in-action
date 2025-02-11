package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository : JpaRepository<Achievement, Long> {

    // SELECT * FROM achievement WHERE is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Achievement>
}
