package com.connector.message.model.ticket

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TicketRepository : ReactiveMongoRepository<Ticket, String> {

}