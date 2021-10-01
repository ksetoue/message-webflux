package com.connector.message.service

import com.connector.message.error.ResourceNotFoundException
import com.connector.message.model.message.Message
import com.connector.message.model.message.MessageRepository
import com.connector.message.model.ticket.Ticket
import com.connector.message.model.ticket.TicketRepository
import com.connector.message.port.command.TicketRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val messageRepository: MessageRepository
) {
    fun create(request: TicketRequest): Mono<Ticket> {
        return ticketRepository.save(
            Ticket(
                customerId = request.customerId,
                externalId = request.externalId
            )
        )
    }
    
    fun findAll(): Flux<Ticket> =
        ticketRepository.findAll()
    
    fun findById(id: String): Mono<Ticket> =
        ticketRepository.findById(id)
            .doOnError { throw ResourceNotFoundException("Ticket $id not found") }
    
    fun updateCompany(id: String, request: TicketRequest): Mono<Ticket> =
        findById(id)
            .flatMap {
                val ticketToUpdate = it.copy(
                    customerId = request.customerId,
                    externalId = request.externalId
                )
                ticketRepository.save(ticketToUpdate)
            }
            .doOnSuccess { updateTicketsMessages(it).subscribe() }
    
    fun updateTicketsMessages(updatedTicket: Ticket): Flux<Message> {
        return messageRepository.saveAll(
            messageRepository.findAllByTicketId(updatedTicket.id!!)
                .map {
                    val messageToUpdate = it.copy(ticket = updatedTicket)
                    
                    messageToUpdate
                }
        )
    }
    
    fun deleteById(id: String): Mono<Void> =
        findById(id)
            .flatMap(ticketRepository::delete)
}