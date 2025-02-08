package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectSkillRepository : JpaRepository<Skill, Long> {
}
