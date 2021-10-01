package com.connector.message.model.ticket

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tickets")
data class Ticket(
    @Id
    val id: String? = null,
    val customerId: String,
    val externalId: String
)