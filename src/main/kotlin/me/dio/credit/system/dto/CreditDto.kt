package me.dio.credit.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import me.dio.credit.system.domain.Credit
import me.dio.credit.system.domain.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(

    @field:NotNull(message = "Invalid input, some data is missing")
    val creditValue: BigDecimal,

    @field:Future(message = "Invalid date")
    val dayFirstOfInstallment: LocalDate,

    @Size(max = 8)
    val numberOfInstallment: Int,

    @field:NotNull(message = "Invalid input, some data is missing")
    val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
