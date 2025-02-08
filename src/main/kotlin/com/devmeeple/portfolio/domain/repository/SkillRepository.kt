package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<ProjectSkill, Long> {
}
