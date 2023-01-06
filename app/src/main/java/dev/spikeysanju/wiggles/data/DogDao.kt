package dev.spikeysanju.wiggles.data

import androidx.room.*


@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogEntity)

    @Query("SELECT * FROM `dog_table`")
    suspend fun getDogs(): List<DogEntity>

    @Query("SELECT * FROM `dog_table` where _id = :dogId")
    suspend fun getDog(dogId: Int): DogEntity?

    @Update
    suspend fun updateDog(dog: DogEntity)

    @Query("UPDATE `dog_table` SET adopted=:adopted  WHERE _id LIKE :dogId")
    suspend fun updateDogAdopted(dogId: String,adopted: Boolean)

}