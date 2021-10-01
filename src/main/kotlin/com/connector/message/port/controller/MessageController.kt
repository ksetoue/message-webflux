package com.connector.message.port.controller

import com.connector.message.port.command.MessageRequest
import com.connector.message.port.command.MessageResponse
import com.connector.message.service.MessageService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/message")
class MessageController(
    private val messageService: MessageService
) {
    @PostMapping
    fun create(@RequestBody request: MessageRequest): Mono<MessageResponse> {
        return messageService.create(request)
            .map { MessageResponse.fromEntity(it) }
    }
    
    @GetMapping
    fun findAll(): Flux<MessageResponse> {
        return messageService.findAll()
            .map { MessageResponse.fromEntity(it) }
    }
}