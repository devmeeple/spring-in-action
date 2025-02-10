package com.devmeeple.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

// TODO: 2025.02.08 MappedSuperclass
// TODO: 2025.02.08 테이블 관계 표현; @Column, Option;
@MappedSuperclass
abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDateTime: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
}
