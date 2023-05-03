package com.example.domain

import org.springframework.data.jpa.repository.JpaRepository

interface DataRepository: JpaRepository<DataEntity, Long> {
}