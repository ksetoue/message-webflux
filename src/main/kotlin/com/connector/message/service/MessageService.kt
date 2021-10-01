package com.connector.message.service

import com.connector.message.error.ResourceNotFoundException
import com.connector.message.model.message.Message
import com.connector.message.model.message.MessageRepository
import com.connector.message.model.ticket.TicketRepository
import com.connector.message.port.command.MessageRequest
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MessageService(
    private val ticketRepository: TicketRepository,
    private val messageRepository: MessageRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    fun create(request: MessageRequest): Mono<Message> {
        logger.info("Starting create message request $request")
        val startTime = System.currentTimeMillis()
        val ticketId = request.ticketId
        
        if (ticketId == null) {
            val totalTime = System.currentTimeMillis() - startTime
            val message = createMessageWithoutTicket(request)
            logger.info("Request took: ${totalTime}ms")
            return message
        } else {
            val message = createMessageWithTicket(ticketId, request)
            val totalTime = System.currentTimeMillis() - startTime
    
            logger.info("Request took: ${totalTime}ms")
            return message
        }
    
    }
    
    fun findAll(): Flux<Message> =
        messageRepository.findAll()
    
    fun findAllByTicketId(id: String): Flux<Message> =
        messageRepository.findAllByTicketId(id)
    
    fun findById(id: ObjectId): Mono<Message> =
        messageRepository.findById(id)
            .doOnError { throw ResourceNotFoundException("Message $id not found") }
    
    
    private fun createMessageWithoutTicket(request: MessageRequest): Mono<Message> {
        return messageRepository.save(
            Message(
                title = request.title,
                content = request.content,
                ticket = null
            )
        )
    }
    
    private fun createMessageWithTicket(ticketId: String, request: MessageRequest): Mono<Message> =
        ticketRepository.findById(ticketId)
            .flatMap {
                messageRepository.save(
                    Message(
                        title = request.title,
                        content = request.content,
                        ticket = it
                    )
                )
            }
    
    private fun requestToString(request: ServerHttpRequest): String =
        "${request.method} ${request.path}"
}