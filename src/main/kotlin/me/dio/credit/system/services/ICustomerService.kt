package me.dio.credit.system.services

import me.dio.credit.system.domain.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}