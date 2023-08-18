package com.cleonildo.petshop.br.pet.shop.repositories

import com.cleonildo.petshop.br.pet.shop.domain.Pet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PetRepository : MongoRepository<Pet, String> {
    override fun findById(id: String): Optional<Pet>
    fun findPetsByName(name: String): List<Pet>
}