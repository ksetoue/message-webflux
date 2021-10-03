package com.connector.message.configuration

import com.ibm.cloud.sdk.core.security.Authenticator
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.assistant.v2.Assistant
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WatsonAssistant(
    @Value("\${watson.api-key}")
    private val watsonApiKey: String,
) {
    
    @Bean
    fun assistant(): Assistant {
        val authenticator: Authenticator =
            IamAuthenticator.Builder().apikey(watsonApiKey).build()
        
        return Assistant("2021-06-14", authenticator)
    }
}