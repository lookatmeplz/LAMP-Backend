package com.LAMP.LAMPBackend.db

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@Entity(name = "lamp_diary")
@EntityListeners(AuditingEntityListener::class)
data class DiaryEntity(@Id
                       @Column(name = "diary_id")
                       @GeneratedValue(strategy = GenerationType.AUTO)
                       var id: Int? = null,

                       @CreatedBy
                       @ManyToOne(fetch = FetchType.LAZY)
                       @JoinColumn(name = "user_id")
                       var user: UserEntity,

                       var title: String,
                       var content: String) : AbstractDateEntity()
