package com.connector.message.port.command

import com.connector.message.model.ticket.Ticket

class MessageRequest (
    val title: String,
    val content: String,
    val ticketId: String?
)