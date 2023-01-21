package com.cleonildo.petshop.br.Pet.Shop.Pet

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PetService(private val repository: PetRepository) {

    fun findAllPets(): List<Pet> = repository.findAll()
    fun findById(id: String): Pet? = repository.findById(id).orElse(null)
    fun save(pet: Pet): Pet = repository.save(pet)
    fun deleteById(id: String) = repository.deleteById(id)
}