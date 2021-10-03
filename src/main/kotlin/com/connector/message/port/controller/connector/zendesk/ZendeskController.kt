package com.connector.message.port.controller.connector.zendesk

import com.connector.message.configuration.WatsonAssistant
import com.connector.message.service.ZendeskService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ZendeskController(
    private val zendeskService: ZendeskService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    @PostMapping("/webhook/zendesk/message")
    fun createZendeskMessage() {
    
    }
    
    @PostMapping("/webhook/zendesk/{project}/ticket")
    fun registerZendeskTicket() {
    
    }
    
    @PostMapping("/webhook/zendesk/{project}/message")
    fun registerZendeskMessage() {
    
    }
}