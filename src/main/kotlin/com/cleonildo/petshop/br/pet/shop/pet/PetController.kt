package com.cleonildo.petshop.br.pet.shop.pet

import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/pets")
class PetController(private val service: PetService) {

    @GetMapping
    fun findAll(): List<Pet> = this.service.findAllPets()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Pet {
        return Pet("Linguica", "Cachorro", 3)
    }

    @PostMapping
    fun save(@RequestBody pet: Pet): Pet = this.service.save(pet)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID) = this.service.deleteById(id)
}