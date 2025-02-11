package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProjectRepository : JpaRepository<Project, Long> {

    // SELECT * FROM project WHERE is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Project>

    override fun findById(id: Long): Optional<Project>
}
