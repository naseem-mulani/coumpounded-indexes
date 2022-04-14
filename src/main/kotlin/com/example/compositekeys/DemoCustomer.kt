package com.example.compositekeys

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document

@Document("demoCustomer")
@CompoundIndex(unique = true, name = "customerIndex", def = "{'referenceId': 1, 'emailId': 1}")
data class DemoCustomer(

    val referenceId: String,
    val emailId: String,
    val status: String
)