package com.connector.message.model.channel

enum class ChannelType() { ZAMMAD, ZENDESK }

data class Channel(val ticketId: String = "", val type: ChannelType)