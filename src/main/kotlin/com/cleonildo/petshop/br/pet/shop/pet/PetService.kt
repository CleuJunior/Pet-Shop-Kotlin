package com.cleonildo.petshop.br.pet.shop.pet

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class PetService(private val repository: PetRepository) {

    fun findAllPets(): List<Pet> = repository.findAll()
    fun findById(id: UUID): Pet? = repository.findById(id).get()
    fun save(pet: Pet): Pet = repository.save(pet)
    fun deleteById(id: UUID) = repository.deleteById(id)
}