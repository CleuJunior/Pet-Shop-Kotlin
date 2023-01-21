package com.cleonildo.petshop.br.Pet.Shop.Pet

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "pets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Pet {

    @Id
    var id: String = ""
    var name: String = ""
    var type: String = ""
    var age: Int = 0
}