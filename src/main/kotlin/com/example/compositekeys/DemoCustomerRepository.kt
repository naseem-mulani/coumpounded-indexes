package com.example.compositekeys

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface DemoCustomerRepository : ReactiveMongoRepository<DemoCustomer, String> {
    fun findByReferenceIdAndEmailId(referenceId: String, emailId: String): Mono<DemoCustomer>
}