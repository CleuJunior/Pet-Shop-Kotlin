package com.cleonildo.petshop.br.Pet.Shop.Pet

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : MongoRepository<Pet, String> {
}