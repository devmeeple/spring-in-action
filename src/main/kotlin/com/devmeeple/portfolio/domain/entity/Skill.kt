package com.devmeeple.portfolio.domain.entity

import com.devmeeple.portfolio.domain.constant.SkillType
import jakarta.persistence.*

// TODO: 2025.02.09 SkillType.valueOf(type); DBMS 예약어 type; EnumTypeORDINAL vs. EnumType.String;
@Entity
class Skill(
    name: String,
    type: String,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    var id: Long? = null

    var name: String = name

    @Column(name = "skill_type")
    @Enumerated(value = EnumType.STRING)
    var type: SkillType = SkillType.valueOf(type)

    var isActive: Boolean = isActive
}
