package com.LAMP.LAMPBackend.user

import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.EntityManager

@RunWith(SpringRunner::class)
@DataJpaTest(showSql = true)
class UserTest {
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var entityManager: EntityManager

    @Before
    fun setup() {
        userRepository.save(User(name = "alice", email = "alice@gmail.com"))
        userRepository.save(User(name = "bob", email = "bob@gmail.com"))
        entityManager.clear()
    }

    @Test
    fun simple() {
        println(userRepository.findByName("bob"))
    }
}