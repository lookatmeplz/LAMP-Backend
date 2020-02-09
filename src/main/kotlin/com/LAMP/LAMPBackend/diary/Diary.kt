package com.LAMP.LAMPBackend.diary

import com.LAMP.LAMPBackend.user.User
import java.util.*
import javax.persistence.*

@Entity
data class Diary(@Id
                 @GeneratedValue(strategy = GenerationType.AUTO)
                 var id: Int? = null,
                 @ManyToOne(fetch = FetchType.LAZY)
                 @JoinColumn(name = "user_id")
                 var user: User,
                 var date: Date?,
                 var title: String,
                 var content: String)
