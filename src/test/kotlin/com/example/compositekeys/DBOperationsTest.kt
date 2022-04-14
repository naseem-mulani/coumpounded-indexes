package com.example.compositekeys

import io.kotlintest.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class DBOperationsTest {

    val customerRepository = mockk<DemoCustomerRepository>()
    {
        every { save(any()) } returns Mono.just( DemoCustomer("111", "test@gmail.com", "SUCCESS"))
    }
    val dbOperations = DBOperations(customerRepository)


    @Test
    fun `should save customer`() {
        val result = dbOperations.save("111", "test@gmail.com")
        StepVerifier.create(result)
            .consumeNextWith {
                it shouldNotBe  null
            }.verifyComplete()
    }
}