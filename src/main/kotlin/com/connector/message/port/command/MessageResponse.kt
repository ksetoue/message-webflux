package com.connector.message.port.command

import com.connector.message.model.message.Message

class MessageResponse(
    val id: String,
    val title: String,
    val content: String,
    val savedAt: String,
    val ticket: TicketResponse?
) {
    companion object {
        fun fromEntity(message: Message): MessageResponse =
            MessageResponse(
                id = message.id!!.toHexString(),
                title = message.title,
                content = message.content,
                savedAt = message.savedAt.toString(),
                ticket = message.ticket?.let { TicketResponse.fromEntity(it) }
            )
    }
}