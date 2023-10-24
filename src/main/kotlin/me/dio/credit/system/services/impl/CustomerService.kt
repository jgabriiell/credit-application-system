package me.dio.credit.system.services.impl

import me.dio.credit.system.domain.Customer
import me.dio.credit.system.services.ICustomerService
import org.springframework.stereotype.Service
import  me.dio.credit.system.repositories.CustomerRepository
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw RuntimeException("Id not found")
        }

    override fun delete(id: Long) =
        this.customerRepository.deleteById(id)

}