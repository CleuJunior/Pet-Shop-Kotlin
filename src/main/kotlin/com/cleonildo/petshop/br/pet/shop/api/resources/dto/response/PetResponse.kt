package com.cleonildo.petshop.br.pet.shop.api.resources.dto.response

import com.cleonildo.petshop.br.pet.shop.domain.Pet
import java.time.LocalDate
import java.time.Period

class PetResponse(pet: Pet) {
    val id: String? = pet.id
    val name: String = pet.name
    val specie: String = pet.specie.type
    val age: Int = Period.between(pet.birthdate, LocalDate.now()).years
}