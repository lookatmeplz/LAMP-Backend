package com.LAMP.LAMPBackend.diary

import com.LAMP.LAMPBackend.user.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("diary")
class LampController (val diaryRepository: DiaryRepository,
                      val userRepository: UserRepository) {
    @GetMapping
    fun main(): String {
        return "main diary"
    }

    @GetMapping("/pages/{id}")
    fun pages(@PathVariable("id") id: Int = 0): Page<DiaryEntity>? {
        val pageable = PageRequest.of(0, 5, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)

        return when (page.isEmpty) {
            false -> page
            else -> null
        }
    }

    @GetMapping("{id}")
    fun diary(@PathVariable("id") id: Int?): DiaryEntity? {
        val diaryId: Int = id?: diaryRepository.count().toInt()
        val diary = diaryRepository.findById(diaryId)

        return when (diary.isEmpty) {
            false -> diary.get()
            else -> null
        }
    }

    @PostMapping
    fun addDiary(@RequestHeader("username") username: String,
                 @RequestHeader("title") title: String,
                 @RequestHeader("content") content: String): Boolean {
        val user = userRepository.findByUserName(username) ?: return false
        val diary = DiaryEntity(user = user, title = title, content = content)
        diaryRepository.save(diary)
        return true;
    }

}
