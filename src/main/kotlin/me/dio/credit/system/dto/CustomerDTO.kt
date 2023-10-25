package me.dio.credit.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.system.domain.Address
import me.dio.credit.system.domain.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty(message = "Invalid input, some data is missing")
    val firstName: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val lastName: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    @field:CPF(message = "This CPF is invalid, please try again")
    val cpf: String,

    @field:NotNull(message = "Invalid input, some data is missing")
    val income: BigDecimal,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    @field:Email(message = "This email is invalid")
    val email: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val password: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val zipCode: String,

    @field:NotEmpty(message = "Invalid input, some data is missing")
    val street: String
) {
    fun toEntity(): Customer = Customer (
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
