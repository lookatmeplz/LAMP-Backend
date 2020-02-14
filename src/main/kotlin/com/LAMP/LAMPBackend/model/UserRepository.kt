package com.LAMP.LAMPBackend.model

import com.LAMP.LAMPBackend.db.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Int> {
    fun findByUsername(name: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
}