package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SyncTaskExecutor
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class ExecutorConfig {

    @Bean
    fun syncEmailExecutor(): Executor = SyncTaskExecutor()
}

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
class CustomController(
    private val customService: CustomService
) {
    @GetMapping("/customs")
    fun customs(): ResponseEntity<*> =
        customService.doWork().let { ResponseEntity.accepted().build<Any>() }
}

@Component
class CustomService {
    @Async
    fun doWork(): UUID = UUID.randomUUID()
}
