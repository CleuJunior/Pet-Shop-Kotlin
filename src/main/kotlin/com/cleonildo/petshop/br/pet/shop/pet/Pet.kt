package com.cleonildo.petshop.br.pet.shop.pet

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "pets")
class Pet(var name: String, var type: String, var age: Int) {

    @Id
    val id: UUID = UUID.randomUUID()
}