package com.devmeeple.portfolio.domain.repository

import com.devmeeple.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository: JpaRepository<Link, Long> {
}
