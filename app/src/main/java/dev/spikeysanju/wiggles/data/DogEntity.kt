package dev.spikeysanju.wiggles.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.spikeysanju.wiggles.model.Owner

@Entity(tableName = "dog_table")
data class DogEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: Int,
    val name: String,
    val age: Double,
    val gender: String,
    val color: String,
    val weight: Double,
    val location: String,
    val image: Int,
    val about: String,
    @Embedded
    val owner: Owner,
    var adopted: Boolean = false
)
