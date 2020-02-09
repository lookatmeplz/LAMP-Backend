package com.LAMP.LAMPBackend.diary

import org.springframework.data.repository.CrudRepository

interface DiaryRepository : CrudRepository<Diary, Int> {

}