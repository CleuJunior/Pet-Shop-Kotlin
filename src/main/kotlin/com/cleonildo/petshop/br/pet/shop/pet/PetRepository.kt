package com.cleonildo.petshop.br.pet.shop.pet

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.Optional

@Repository
interface PetRepository : MongoRepository<Pet, UUID> {
    override fun findById(id: UUID): Optional<Pet>
}