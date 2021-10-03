package com.connector.message.configuration.clients

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zendesk.client.v2.Zendesk

@Configuration
class ZendeskClient(
    @Value("\${zendesk.bot.username}")
    private val zendeskBotUsername: String,
    @Value("\${zendesk.bot.password}")
    private val zendeskBotPassword: String,
    @Value("\${zendesk.url}")
    private val zendeskUrl: String
) {
    
    @Bean
    fun zendesk(): Zendesk {
        return Zendesk.Builder(zendeskUrl)
            .setUsername(zendeskBotUsername)
            .setPassword(zendeskBotPassword)
            .build()
    }
}