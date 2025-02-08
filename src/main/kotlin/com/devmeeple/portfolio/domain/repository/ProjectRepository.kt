package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
}
