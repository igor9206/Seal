package ru.kor.seal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kor.seal.dao.ObjectiveDao
import ru.kor.seal.dao.StageDao
import ru.kor.seal.entity.ObjectiveEntity
import ru.kor.seal.entity.StageEntity

@Database(
    entities = [
        ObjectiveEntity::class,
        StageEntity::class
    ], version = 1
)
abstract class AppDb : RoomDatabase() {

    abstract fun objectiveDao(): ObjectiveDao

    abstract fun stageDao(): StageDao
}