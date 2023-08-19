package com.cleonildo.petshop.br.pet.shop.services

import com.cleonildo.petshop.br.pet.shop.api.resources.dto.request.PetRequest
import com.cleonildo.petshop.br.pet.shop.api.resources.dto.response.PetResponse
import com.cleonildo.petshop.br.pet.shop.domain.AnimalSpecie
import com.cleonildo.petshop.br.pet.shop.domain.Pet
import com.cleonildo.petshop.br.pet.shop.repositories.PetRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.Period
import java.util.*

@ExtendWith(MockitoExtension::class)
class PetServiceTest {

    @Mock
    private lateinit var repository: PetRepository
    private lateinit var service: PetService
    private lateinit var pet: Pet
    private lateinit var response: PetResponse
    private val petName: String = "Belinha"
    private val birthdate: LocalDate = LocalDate.of(2002, 2, 13)
    private val age: Int = Period.between(birthdate, LocalDate.now()).years
    private val specie: AnimalSpecie = AnimalSpecie.DOG

    @BeforeEach
    fun setup() {
        service = PetService(repository)
        pet = Pet(petName, birthdate, specie)
        response = PetResponse(pet)
    }

    @Test
    fun shouldReturnPagealbeOfPets() {
        val pageable: Pageable = Pageable.ofSize(1).withPage(0)
        val pets: List<Pet> = listOf(pet)
        val page: Page<Pet> = PageImpl(pets, pageable, pets.size.toLong())

        `when`(repository.findAll(pageable)).thenReturn(page)

        val actual: Page<PetResponse> = service.findAllPetsPagination(pageable)

        // Assertions
        assertEquals(1, actual.content.size)
        assertEquals(petName, actual.content[0].name)
        assertEquals(age, actual.content[0].age)
        assertEquals(specie.type, actual.content[0].specie)

        // Verifications
        verify(repository, times(1)).findAll(pageable)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun shouldReturnSingleListByName() {
        val pets: List<Pet> = listOf(pet)

        `when`(repository.findPetsByName(petName)).thenReturn(pets)

        val actual: PetResponse = service.findPetByName(petName)[0]

        // Assertions
        assertEquals(petName, actual.name)
        assertEquals(age, actual.age)
        assertEquals(specie.type, actual.specie)

        // Verifications
        verify(repository, times(1)).findPetsByName(petName)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun shouldReturnPetById() {
        `when`(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(pet))

        val actual: PetResponse = service.findPetById(ArgumentMatchers.anyString())

        // Assertions
        assertEquals(petName, actual.name)
        assertEquals(age, actual.age)
        assertEquals(specie.type, actual.specie)

        // Verifications
        verify(repository, times(1)).findById(ArgumentMatchers.anyString())
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun shouldSavePet() {
        `when`(repository.save(pet)).thenReturn(pet)

        val request = PetRequest(petName, birthdate, specie)

        val actual: PetResponse = service.savePet(request)

        // Assertions
        assertEquals(petName, actual.name)
        assertEquals(age, actual.age)
        assertEquals(specie.type, actual.specie)

        // Verifications
        verify(repository, times(1)).save(pet)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun shoulUpdatePet() {
        val request = PetRequest("Felinerio", LocalDate.of(2012, 1, 10), AnimalSpecie.CAT)
        val petUpdate = Pet("Felinerio", LocalDate.of(2012, 1, 10), AnimalSpecie.CAT)
        `when`(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(pet))

        service.updatePet(ArgumentMatchers.anyString(), request)

        // Assert
        verify(repository).findById(ArgumentMatchers.anyString())
        verify(repository).save(petUpdate)
    }

    @Test
    fun shouldDelete() {
        doNothing().`when`(repository).deleteById(ArgumentMatchers.anyString())

        service.deleteById(ArgumentMatchers.anyString())

        // Assert
        verify(repository).deleteById(ArgumentMatchers.anyString())
    }
}