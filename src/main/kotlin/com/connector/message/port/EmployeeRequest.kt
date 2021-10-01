package com.connector.message.port

class EmployeeRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyId: String?
)