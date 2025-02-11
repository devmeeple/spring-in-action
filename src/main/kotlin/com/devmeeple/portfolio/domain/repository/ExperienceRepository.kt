package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExperienceRepository : JpaRepository<Experience, Long> {

    // SELECT * FROM experience WHERE is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Experience>

    override fun findById(id: Long): Optional<Experience>
}
