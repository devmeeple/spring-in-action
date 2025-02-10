package com.devmeeple.portfolio.domain.entity

import jakarta.persistence.*

// TODO: 2025.02.10 단항뱡 연관관계
@Entity
class ExperienceDetail(
    content: String,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_detail_id")
    var id: Long? = null

    var content: String = content

    var isActive: Boolean = isActive

    fun update(content: String, isActive: Boolean) {
        this.content = content
        this.isActive = isActive
    }

}
