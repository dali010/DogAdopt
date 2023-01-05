package dev.spikeysanju.wiggles.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.spikeysanju.wiggles.data.DogDao
import dev.spikeysanju.wiggles.data.DogEntity

@Database(
    entities = [DogEntity::class],
    version = 1
)
abstract class DogDatabase : RoomDatabase() {
    abstract val dogDao: DogDao

    companion object {
        const val DATABASE_NAME = "adoptPet_db"
    }
}