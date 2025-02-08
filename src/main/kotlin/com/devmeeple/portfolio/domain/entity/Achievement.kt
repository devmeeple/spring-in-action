package com.devmeeple.portfolio.domain.entity

import jakarta.persistence.*

// TODO: 2025.02.08 기본키(Primary Key) 생성 전략; PK naming; id : Type? = null
@Entity
class Achievement : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null

}
