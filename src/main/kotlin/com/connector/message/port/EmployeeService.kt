package com.connector.message.port

import com.connector.message.model.Employee
import com.connector.message.model.EmployeeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmployeeService(
    private val companyService: CompanyService,
    private val employeeRepository: EmployeeRepository
) {
    fun createEmployee(request: EmployeeRequest): Mono<Employee> {
        val companyId = request.companyId
        return createEmployeeWithoutCompany(request)
    }
    
    
    private fun createEmployeeWithoutCompany(request: EmployeeRequest): Mono<Employee> {
        return employeeRepository.save(
            Employee(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                company = null
            )
        )
    }
}