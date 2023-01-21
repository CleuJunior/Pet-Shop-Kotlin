package com.cleonildo.petshop.br.Pet.Shop.Pet

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
class PetController(private val service: PetService) {

    @GetMapping
    fun findAll(): List<Pet> = service.findAllPets()

    @GetMapping("/{id}")
    fun findById(id: String): Pet? = service.findById(id)

    @PostMapping
    fun save(@RequestBody pet: Pet): Pet = service.save(pet)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = service.deleteById(id)
}