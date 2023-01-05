package dev.spikeysanju.wiggles.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.spikeysanju.wiggles.data.FakeDogDatabase
import dev.spikeysanju.wiggles.data.DogDatabase
import dev.spikeysanju.wiggles.data.DogDatabase.Companion.DATABASE_NAME
import dev.spikeysanju.wiggles.data.DogLocalSource
import dev.spikeysanju.wiggles.data.DogRepositoryImpl
import dev.spikeysanju.wiggles.domain.DogRepository
import dev.spikeysanju.wiggles.mapper.toDogEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Volatile
    private var INSTANCE: DogDatabase? = null

    private class DogDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> /*database ->*/
                scope.launch {
                    FakeDogDatabase.dogList.map {
                        database.dogDao.insertDog(it.toDogEntity())
                    }
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideDogDatabase(app: Application): DogDatabase {
        return INSTANCE ?: synchronized(this) {
            val scope = CoroutineScope(Dispatchers.IO)
            val instance = Room.databaseBuilder(
                app,
                DogDatabase::class.java,
                DATABASE_NAME
            ).addCallback(DogDatabaseCallback(scope)).fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            instance
        }
    }

    @Singleton
    @Provides
    fun provideDogDao(db: DogDatabase) = db.dogDao

    @Provides
    @Singleton
    fun provideDogRepository(
        dogLocalSource: DogLocalSource
    ): DogRepository {
        return DogRepositoryImpl(
            dogLocalSource
        )
    }
}