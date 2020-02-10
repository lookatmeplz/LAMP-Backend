package com.LAMP.LAMPBackend.diary

import com.LAMP.LAMPBackend.user.User
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "lamp_diary")
data class Diary(@Id
                 @Column(name = "diary_id")
                 @GeneratedValue(strategy = GenerationType.Auto)
                 var id: Int? = null,

                 @ManyToOne(fetch = FetchType.LAZY)
                 @JoinColumn(name = "user_id")
                 var user: User,

                 @Column(name = "created_at")
                 @Temporal(TemporalType.TIMESTAMP)
                 var createdAt: Date,
                 @Column(name = "updated_at")
                 @Temporal(TemporalType.TIMESTAMP)
                 var updatedAt: Date,

                 var title: String,
                 var content: String)
