package com.example.initialize

import com.example.domain.DataEntity
import com.example.domain.DataRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.stream.IntStream
import kotlin.streams.toList

@Component
class DataInsertInitializer(
    private val dataRepository: DataRepository
): InitializingBean {

    override fun afterPropertiesSet() {
        val entityList = IntStream.range(0, 10000)
            .toList()
            .map {
                return@map DataEntity("data $it", "title $it")
            }
            .toList()

        dataRepository.saveAll(entityList)
    }
}