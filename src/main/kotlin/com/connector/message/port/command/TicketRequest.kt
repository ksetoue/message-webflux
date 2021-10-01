package com.connector.message.port.command

import com.connector.message.model.message.Message

class TicketRequest(
    val customerId: String,
    val externalId: String
)