package com.devmeeple.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

// TODO: 2025.02.08 기본키(Primary Key) 생성 전략; PK naming; id : Type? = null
// TODO: 2025.02.08 Class should have [public, protected] no-arg constructor.
@Entity
class Achievement(
    title: String,
    description: String,
    achievedDate: LocalDate?,
    host: String,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null

    var title: String = title

    var description: String = description

    var achievedDate: LocalDate? = achievedDate

    var host: String = host

    var isActive: Boolean = isActive

}
