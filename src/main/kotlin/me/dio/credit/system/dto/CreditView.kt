package me.dio.credit.system.dto

import me.dio.credit.system.domain.Credit
import java.math.BigDecimal
import java.util.*
import me.dio.credit.system.enummeration.Status

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
    val status: Status,
    val emailCostumer: String?,
    val income: BigDecimal?
) {

    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallment,
        status = credit.status,
        emailCostumer = credit.customer?.email,
        income = credit.customer?.income
    )
}
