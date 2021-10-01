package com.connector.message.port.controller

import com.connector.message.port.command.TicketRequest
import com.connector.message.port.command.TicketResponse
import com.connector.message.service.TicketService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/ticket")
class TicketController(
    private val ticketService: TicketService
) {
    @PostMapping
    fun create(@RequestBody request: TicketRequest): Mono<TicketResponse> {
        return ticketService.create(request)
            .map { TicketResponse.fromEntity(it) }
    }
    
    @GetMapping
    fun findAll(): Flux<TicketResponse> {
        return ticketService.findAll()
            .map { TicketResponse.fromEntity(it) }
    }
}