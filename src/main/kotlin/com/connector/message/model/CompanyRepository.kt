package com.connector.message.model

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CompanyRepository : ReactiveMongoRepository<Company, String>