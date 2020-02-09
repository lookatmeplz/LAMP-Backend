package com.LAMP.LAMPBackend

import javax.persistence.*

@Entity
data class User(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Int? = null,
                var name: String,
                var email: String)
