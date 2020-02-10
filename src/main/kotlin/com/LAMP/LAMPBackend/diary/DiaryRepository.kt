package com.LAMP.LAMPBackend.diary

import org.springframework.data.repository.PagingAndSortingRepository

interface DiaryRepository : PagingAndSortingRepository<DiaryEntity, Int>
