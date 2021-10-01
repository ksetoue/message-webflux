package com.connector.message.port

import com.connector.message.error.NotFoundException
import com.connector.message.model.Company
import com.connector.message.model.CompanyRepository
import com.connector.message.model.Employee
import com.connector.message.model.EmployeeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository
) {
    fun createCompany(request: CompanyRequest): Mono<Company> =
        companyRepository.save(
            Company(
                name = request.name,
                address = request.address
            )
        )
    
    fun findAll(): Flux<Company> =
        companyRepository.findAll()
    
    private fun updateCompanyEmployees(updatedCompany: Company): Flux<Employee> =
        employeeRepository.saveAll(
            employeeRepository.findByCompanyId(updatedCompany.id!!)
                .map { it.apply { company = updatedCompany } }
        )
}