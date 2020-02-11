package com.LAMP.LAMPBackend.user

import javax.persistence.*

@Entity(name = "lamp_user")
data class UserEntity(@Id
                      @Column(name = "user_id")
                      @GeneratedValue(strategy = GenerationType.AUTO)
                      val id: Int? = null,
                      var name: String,
                      var email: String)
