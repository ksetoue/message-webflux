package com.connector.message.model.ticket

import com.connector.message.model.channel.Channel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@Document("tickets")
data class Ticket(
    @Id
    val id: String? = null,
    val customerId: String,
    val externalId: String,
    val savedAt: String? = OffsetDateTime.now().toString(),
    val channelType: Channel

)