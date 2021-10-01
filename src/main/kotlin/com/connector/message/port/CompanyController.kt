package com.connector.message.port

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
@RequestMapping("/api/company")
class CompanyController(
    private val companyService: CompanyService
) {
    @PostMapping
    fun createCompany(@RequestBody request: CompanyRequest): Mono<CompanyResponse> {
        return companyService.createCompany(request)
            .map { CompanyResponse.fromEntity(it) }
    }
    
    @GetMapping
    fun findAllCompanies(): Flux<CompanyResponse> {
        return companyService.findAll()
            .map { CompanyResponse.fromEntity(it) }
            .delayElements(Duration.ofSeconds(2))
    }
    
}