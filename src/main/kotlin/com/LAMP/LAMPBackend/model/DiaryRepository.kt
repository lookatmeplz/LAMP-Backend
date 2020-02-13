package com.LAMP.LAMPBackend.model

import com.LAMP.LAMPBackend.db.DiaryEntity
import org.springframework.data.repository.PagingAndSortingRepository

interface DiaryRepository : PagingAndSortingRepository<DiaryEntity, Int>
