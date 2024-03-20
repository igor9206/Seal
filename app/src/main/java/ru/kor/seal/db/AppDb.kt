package ru.kor.seal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kor.seal.dao.ObjectiveDao
import ru.kor.seal.entity.ObjectiveEntity

@Database(
    entities = [ObjectiveEntity::class], version = 1
)
abstract class AppDb : RoomDatabase() {

    abstract fun objectiveDao(): ObjectiveDao
}