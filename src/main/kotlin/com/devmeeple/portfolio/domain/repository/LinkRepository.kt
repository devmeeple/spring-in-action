package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {

    // SELECT * FROM link WHERE is_active: isActive
    fun findAllByIsActive(isActive: Boolean): List<Link>
}
