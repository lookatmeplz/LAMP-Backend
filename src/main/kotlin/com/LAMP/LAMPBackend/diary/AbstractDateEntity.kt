package com.LAMP.LAMPBackend.diary

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractDateEntity(@CreatedDate
                                  @Column(name = "created_at")
                                  var createdAt: LocalDateTime? = null,
                                  @LastModifiedDate
                                  @Column(name = "updated_at")
                                  var updatedAt: LocalDateTime? = null)
