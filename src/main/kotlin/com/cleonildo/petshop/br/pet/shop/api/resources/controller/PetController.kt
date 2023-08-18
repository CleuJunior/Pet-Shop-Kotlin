package com.cleonildo.petshop.br.pet.shop.api.resources.controller

import com.cleonildo.petshop.br.pet.shop.api.resources.dto.request.PetRequest
import com.cleonildo.petshop.br.pet.shop.api.resources.dto.response.PetResponse
import com.cleonildo.petshop.br.pet.shop.services.PetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
class PetController(private val service: PetService) {

    @GetMapping
    fun findAllPets(pageable: Pageable): ResponseEntity<List<PetResponse>> {
        val page: Page<PetResponse> = this.service.findAllPetsPagination(pageable)
        return ResponseEntity.ok().body(page.content)
    }

    @GetMapping("/name/{name}")
    fun findAllPetsByName(@PathVariable("name") name: String): ResponseEntity<List<PetResponse>> {
        val pets: List<PetResponse> = service.findPetByName(name)
        return ResponseEntity.ok().body(pets)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<PetResponse>  {
        val pet = this.service.findPetById(id)
        return ResponseEntity.ok().body(pet)
    }

    @PostMapping
    fun savePet(@RequestBody request: PetRequest): ResponseEntity<PetResponse> {
        val pet = this.service.savePet(request)
        return ResponseEntity.accepted().body(pet)
    }

    @PutMapping("update/{id}")
    fun updatePet(@PathVariable("id") id: String, @RequestBody request: PetRequest): ResponseEntity<Unit> {
        service.updatePet(id, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<Unit>  {
        this.service.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}