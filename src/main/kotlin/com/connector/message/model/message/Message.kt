package com.connector.message.model.message

import com.connector.message.model.ticket.Ticket
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@Document("messages")
data class Message(
    @Id
    val id: ObjectId? = null,
    var title: String,
    var content: String,
    val savedAt: String? = OffsetDateTime.now().toString(),
    val ticket: Ticket?
)