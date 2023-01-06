package dev.spikeysanju.wiggles.mapper

import dev.spikeysanju.wiggles.data.DogEntity
import dev.spikeysanju.wiggles.model.Dog

fun Dog.toDogEntity(): DogEntity {
    return DogEntity(
        _id = id,
        name = name,
        age = age,
        gender = gender,
        color = color,
        weight = weight,
        location = location,
        image = image,
        about = about,
        owner = owner,
        adopted = adopted
    )
}

fun DogEntity.toDog(): Dog {
    return Dog(
        id = _id,
        name = name,
        age = age,
        gender = gender,
        color = color,
        weight = weight,
        location = location,
        image = image,
        about = about,
        owner = owner,
        adopted = adopted
    )
}