package com.example.compositekeys

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DBOperations(
    @Autowired val customerRepository: DemoCustomerRepository
) {
    fun save(referenceId: String, emailId: String): Mono<DemoCustomer> {
        return customerRepository.save(
            DemoCustomer(
                referenceId,
                emailId,
                "Success"
            )
        ).map {
            it
        }.onErrorReturn(
            DemoCustomer(
                referenceId, emailId,
                status = "FAILURE"
            )
        )
    }

    fun get(referenceId: String): Mono<DemoCustomer> {
        return customerRepository.findByReferenceIdAndEmailId(referenceId, "test@email.com")
            .onErrorReturn(
                DemoCustomer(
                    referenceId,
                    "",
                    status = "FAILURE"
                )
            ).log()
    }
}