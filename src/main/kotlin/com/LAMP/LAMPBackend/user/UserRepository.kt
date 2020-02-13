package com.LAMP.LAMPBackend.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Int> {
    fun findByUserName(name: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
}