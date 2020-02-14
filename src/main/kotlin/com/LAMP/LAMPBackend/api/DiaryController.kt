package com.LAMP.LAMPBackend.api

import com.LAMP.LAMPBackend.db.DiaryEntity
import com.LAMP.LAMPBackend.model.DiaryRepository
import com.LAMP.LAMPBackend.model.UserRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Api(tags = ["2. Diary"])
@RestController
@RequestMapping("diary")
class DiaryController (val diaryRepository: DiaryRepository,
                      val userRepository: UserRepository) {
    @GetMapping
    fun main(): String {
        return "main diary"
    }

    @ApiOperation(value = "5개의 diary 조회", notes = "특정 위치부터 5개의 diary를 조회한다")
    @GetMapping("/pages/{id}")
    fun pages(@ApiParam(value = "diary의 page id", required = true) @PathVariable("id") id: Int = 0): Page<DiaryEntity>? {
        val pageable = PageRequest.of(0, 5, Sort.by("id").descending())
        val page = diaryRepository.findAll(pageable)

        return when (page.isEmpty) {
            false -> page
            else -> null
        }
    }

    @ApiOperation(value = "특정 id의 diary 조회", notes = "특정 id의 1개의 diary를 조회한다")
    @GetMapping("{id}")
    fun diary(@ApiParam(value = "diary id", required = true) @PathVariable("id") id: Int?): DiaryEntity? {
        val diaryId: Int = id?: diaryRepository.count().toInt()
        val diary = diaryRepository.findById(diaryId)

        return when (diary.isEmpty) {
            false -> diary.get()
            else -> null
        }
    }

    @ApiOperation(value = "diary 작성", notes = "새로운 diary를 작성한다")
    @PostMapping
    fun addDiary(@ApiParam(value = "회원아이디", required = true) @RequestHeader("username") username: String,
                 @ApiParam(value = "diary 제목", required = true) @RequestHeader("title") title: String,
                 @ApiParam(value = "diary 내용", required = true) @RequestHeader("content") content: String): Boolean {
        val user = userRepository.findByUsername(username) ?: return false
        val diary = DiaryEntity(user = user, title = title, content = content)
        diaryRepository.save(diary)
        return true;
    }

}
