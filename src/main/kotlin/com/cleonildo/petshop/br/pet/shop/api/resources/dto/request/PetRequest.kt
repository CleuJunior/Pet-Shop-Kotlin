package com.cleonildo.petshop.br.pet.shop.api.resources.dto.request

import com.cleonildo.petshop.br.pet.shop.domain.AnimalSpecie
import java.time.LocalDate

data class PetRequest(val name: String, val birthdate: LocalDate, val specie: AnimalSpecie)
