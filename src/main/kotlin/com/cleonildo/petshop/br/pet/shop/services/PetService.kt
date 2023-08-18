package com.cleonildo.petshop.br.pet.shop.services

import com.cleonildo.petshop.br.pet.shop.api.resources.dto.request.PetRequest
import com.cleonildo.petshop.br.pet.shop.api.resources.dto.response.PetResponse
import com.cleonildo.petshop.br.pet.shop.domain.Pet
import com.cleonildo.petshop.br.pet.shop.repositories.PetRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PetService(private val repository: PetRepository) {

    fun findAllPetsPagination(pageable: Pageable): Page<PetResponse> {
        return repository.findAll(pageable)
            .map { pet -> PetResponse(pet) }
    }

    fun findPetByName(name: String): List<PetResponse> {
        return repository.findPetsByName(name)
            .map { pet -> PetResponse(pet) }
    }

    fun findPetById(id: String): PetResponse {
        return PetResponse(repository.findById(id).get())
    }

    fun savePet(request: PetRequest): PetResponse {
        val pet = Pet(request.name, request.birthdate, request.specie)
        repository.save(pet)

        return PetResponse(pet)
    }

    fun updatePet(id: String, request: PetRequest) {
        val pet = repository.findById(id).get()

        pet.name = request.name
        pet.birthdate = request.birthdate
        pet.specie = request.specie

        repository.save(pet)
    }

    fun deleteById(id: String) = repository.deleteById(id)
}