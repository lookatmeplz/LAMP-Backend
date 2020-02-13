package com.LAMP.LAMPBackend.diary

import com.LAMP.LAMPBackend.db.DiaryEntity
import com.LAMP.LAMPBackend.db.UserEntity
import com.LAMP.LAMPBackend.model.DiaryRepository
import com.LAMP.LAMPBackend.model.UserRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
        assertEquals(userRepository.findByUserName("alice"), diary.user, "findById(1)'s user should be alice")
        assertNotNull(diary.createdAt, "findById(1)'s createdAt should not be null")
        assertNotNull(diary.updatedAt, "findById(1)'s updatedAt should not be null")
    }

    @Test
    fun `Diary Paging Test 1`() {
        val pageable = PageRequest.of(0, 5, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)
        assertEquals(2, page.totalPages, "Total Pages should be 2!")
    }

    @Test
    fun `Diary Paging Test 2`() {
        val pageable = PageRequest.of(0, 5, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)
        assertEquals(10, page.first().id, "Page's first id should be 10")
        assertEquals(6, page.last().id, "Page's first id should be 6")
    }

    @Test
    fun `Diary Paging Test 3`() {
        val pageable = PageRequest.of(0, 50, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)
        assertEquals(10, page.first().id, "Page's first id should be 10")
        assertEquals(1, page.last().id, "Page's first id should be 1")
    }

    @Test
    fun `Diary Paging Test 4`() {
        diaryRepository.deleteById(6)

        val pageable = PageRequest.of(0, 5, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)
        assertEquals(10, page.first().id, "Page's first id should be 10")
        assertEquals(5, page.last().id, "Page's first id should be 1")
    }

    @Test
    fun `Diary Paging Test 5`() {
        diaryRepository.deleteAll()
        val page = diaryRepository.findById(1)
        println(page)
        assertTrue(page.isEmpty)
    }

}