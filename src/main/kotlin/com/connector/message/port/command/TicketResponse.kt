package com.connector.message.port.command

import com.connector.message.model.ticket.Ticket

class TicketResponse(
    val id: String,
    val customerId: String,
    val externalId: String
) {
    companion object {
        fun fromEntity(ticket: Ticket): TicketResponse =
            TicketResponse(
                id = ticket.id!!,
                customerId = ticket.customerId,
                externalId = ticket.externalId
            )
    }
}