package me.dio.credit.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.system.domain.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val firstName: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val lastName: String,

    @field:NotNull(message = "Invalid input, some data is missing")
    val income: BigDecimal,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val zipCode: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val street: String
) {

    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
