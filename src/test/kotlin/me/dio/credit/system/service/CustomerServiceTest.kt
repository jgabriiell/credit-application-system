package me.dio.credit.system.service

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import me.dio.credit.system.domain.Address
import me.dio.credit.system.domain.Customer
import me.dio.credit.system.exceptions.BusinessException
import me.dio.credit.system.repositories.CustomerRepository
import me.dio.credit.system.services.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random as Random1

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @Mock lateinit var customerRepository: CustomerRepository
    @InjectMocks lateinit var customerService: CustomerService

    @Test
    fun `should create costumer`() {
        // given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        // when
        val actual: Customer = customerService.save(fakeCustomer)

        // then
        Assertions.assertThat(actual).isNotNull
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val actual: Customer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        io.mockk.verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        io.mockk.verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        io.mockk.verify(exactly = 1) { customerRepository.findById(fakeId) }
        io.mockk.verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }
    companion object {
        fun buildCustomer(
            firstName: String = "João",
            lastName: String = "Gabriel",
            cpf: String = "91141710315",
            email: String = "gabriel@gmail.com",
            password: String = "36421",
            zipCode: String = "12365",
            street: String = "Rua do joção",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Customer (
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }
}