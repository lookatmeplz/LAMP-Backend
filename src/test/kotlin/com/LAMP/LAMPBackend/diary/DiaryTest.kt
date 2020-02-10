package com.LAMP.LAMPBackend.diary

import com.LAMP.LAMPBackend.user.UserEntity
import com.LAMP.LAMPBackend.user.UserRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@DataJpaTest(showSql = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableJpaAuditing
class DiaryTest (@Autowired val diaryRepository: DiaryRepository,
                 @Autowired val userRepository: UserRepository) {

    @BeforeAll
    fun setup() {
        val user = UserEntity(name = "alice", email = "alice@gmail.com")
        userRepository.save(user)

        for (i in 1..10) {
            val diary = DiaryEntity(user = user, title = "Diary Title $i", content = "Diary Contents $i")
            diaryRepository.save(diary)
        }
    }

    @Test
    fun `Find Diary By id 1`() {
        val diary = diaryRepository.findById(1).get()
        assertNotNull(diary, "findById(1) should not be null!")
    }

    @Test
    fun `Find Diary By id 2`() {
        val diary = diaryRepository.findById(10).get()
        assertNotNull(diary, "findById(10) should not be null!")
    }

    @Test
    fun `Find Diary By ids`() {
        val diaries = diaryRepository.findAllById(1..10)
        diaries.forEach { diary -> assertNotNull(diary, "findAllById(1..10) should not be null!") }
    }

    @Test
    fun `Diary Column Test`() {
        val diary = diaryRepository.findById(1).get()

        assertEquals(1, diary.id, "findById(1)'s id should be 1!")
        assertEquals("Diary Title 1", diary.title, "findById(1)'s title should be 'Diary Title 1'!")
        assertEquals("Diary Contents 1", diary.content, "findById(1)'s id should be 'Diary Contents 1'!")
        assertEquals(userRepository.findByName("alice"), diary.user, "findById(1)'s user should be alice")
        assertNotNull(diary.createdAt, "findById(1)'s createdAt should not be null")
        assertNotNull(diary.updatedAt, "findById(1)'s updatedAt should not be null")
    }

}