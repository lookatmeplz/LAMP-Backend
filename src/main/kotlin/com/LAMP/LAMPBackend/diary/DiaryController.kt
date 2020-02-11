package com.LAMP.LAMPBackend.diary

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("diary")
class LampController (val diaryRepository: DiaryRepository) {
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

    @GetMapping("/diary/{id}")
    fun diary(@PathVariable("id") id: Int?): DiaryEntity? {
        val diaryId: Int = id?: diaryRepository.count().toInt()
        val diary = diaryRepository.findById(diaryId)

        return when (diary.isEmpty) {
            false -> diary.get()
            else -> null
        }
    }

}
