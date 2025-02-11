package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.constant.SkillType
import com.devmeeple.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SkillRepository : JpaRepository<Skill, Long> {

    // SELECT * FROM skill WHERE is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Skill>

    // SELECT * FROM skill WHERE lower(name) = lower(:name) and skill_type = :type
    fun findByNameIgnoreCaseAndType(name: String, type: SkillType): Optional<Skill>
}
