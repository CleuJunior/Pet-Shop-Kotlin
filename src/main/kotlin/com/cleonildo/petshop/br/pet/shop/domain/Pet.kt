package com.cleonildo.petshop.br.pet.shop.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "pets")
data class Pet(
    var name: String,
    var birthdate: LocalDate,
    var specie: AnimalSpecie
) {
    @Id
    var id: String? = null
}