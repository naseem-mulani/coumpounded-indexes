package com.example.compositekeys

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/customer/v1")
@Validated
class CustomerController(
    @Autowired val dbOperations: DBOperations
) {

    @PostMapping("/save")
    fun save(@RequestBody request: SaveCustomerRequest): Mono<String> {
        return dbOperations.save(request.referenceId, request.emailId).map {
            it.status
        }
    }
    @GetMapping("/{referenceId}")
    fun get(@PathVariable referenceId: String): Mono<DemoCustomer> {
        return dbOperations.get(referenceId)
    }

}

data class SaveCustomerRequest(
    val referenceId: String,
    val emailId: String
)