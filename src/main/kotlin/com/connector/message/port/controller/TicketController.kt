package com.connector.message.port.controller

import com.connector.message.port.command.TicketRequest
import com.connector.message.port.command.TicketResponse
import com.connector.message.service.TicketService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
@RequestMapping("/api/ticket")
class TicketController(
    private val ticketService: TicketService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    @PostMapping
    fun create(@RequestBody request: TicketRequest): Mono<TicketResponse> {
        logger.info("Create ticket $request")
        return ticketService.create(request)
            .map { TicketResponse.fromEntity(it) }
    }
    
    @GetMapping
    fun findAll(): Flux<TicketResponse> {
        logger.info("Find all tickets")
        return ticketService.findAll()
            .map { TicketResponse.fromEntity(it) }
    }
}