package com.LAMP.LAMPBackend.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByName(name: String): User?
    fun findByEmail(email: String): User?
}