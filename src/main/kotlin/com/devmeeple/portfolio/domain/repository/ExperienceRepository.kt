package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long> {
}
