package com.connector.message.model.message

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface MessageRepository : ReactiveMongoRepository<Message, ObjectId> {
    fun findAllByTicketId(id: String): Flux<Message>
}