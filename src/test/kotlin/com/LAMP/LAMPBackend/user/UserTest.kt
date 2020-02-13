package com.LAMP.LAMPBackend.user

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@DataJpaTest(showSql = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest (@Autowired val userRepository: UserRepository) {

    @BeforeAll
    fun setup() {
        val user1 = UserEntity(name = "alice", email = "alice@gmail.com")
        val user2 = UserEntity(name = "bob", email = "bob@gmail.com")

        userRepository.save(user1)
        userRepository.save(user2)
    }

    @Test
    fun `Find User By Name 1`() {
        val alice:UserEntity? = userRepository.findByUserName("alice")
        assertNotNull(alice, "findByName('alice') should not be null!")
    }

    @Test
    fun `Find User By Name 2`() {
        val bob:UserEntity? = userRepository.findByUserName("bob")
        assertNotNull(bob, "findByName('bob') should not be null!")
    }

    @Test
    fun `Find User By Name id 1`() {
        val alice:UserEntity? = userRepository.findByUserName("alice")
        assertEquals(1, alice?.id, "Alice's id should be 1")
    }

    @Test
    fun `Find User By Name id 2`() {
        val bob:UserEntity? = userRepository.findByUserName("bob")
        assertEquals(2, bob?.id, "Bob's id should be 1")
    }

    @Test
    fun `Find User By Email 1`() {
        val alice:UserEntity? = userRepository.findByEmail("alice@gmail.com")
        assertNotNull(alice, "findByEmail('alice@gmail.com') should not be null!")
    }

    @Test
    fun `Find User By Email 2`() {
        val bob:UserEntity? = userRepository.findByEmail("bob@gmail.com")
        assertNotNull(bob, "findByEmail('bob@gmail.com') should not be null!")
    }

}