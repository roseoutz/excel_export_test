package com.example.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class DataEntity(
    private var data: String,

    private var title: String,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private var id: Long? = null,

    @CreatedDate
    private var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    private var modifiedDate: LocalDateTime? = null,
) {
    fun toStringArray(): List<String> {
        return listOf(
            id.toString(),
            data,
            title,
            createdDate.toString(),
            modifiedDate.toString()
        )
    }
}