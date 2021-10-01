package com.connector.message.service

import com.connector.message.error.ResourceNotFoundException
import com.connector.message.model.message.Message
import com.connector.message.model.message.MessageRepository
import com.connector.message.model.ticket.TicketRepository
import com.connector.message.port.command.MessageRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MessageService(
    private val ticketRepository: TicketRepository,
    private val messageRepository: MessageRepository
) {
    fun create(request: MessageRequest): Mono<Message> {
        val ticketId = request.ticketId
        
        return if (ticketId == null) {
            createMessageWithoutTicket(request)
        } else {
            createMessageWithTicket(ticketId, request)
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
    
}