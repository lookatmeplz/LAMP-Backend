package com.LAMP.LAMPBackend.user

import javax.persistence.*

@Entity
@Table(name = "lamp_user")
data class User(@Id
                @Column(name = "user_id")
                @GeneratedValue(strategy = GenerationType.AUTO)
                val id: Int? = null,
                var name: String,
                var email: String)
