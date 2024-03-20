package ru.kor.seal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.kor.seal.entity.ObjectiveEntity

@Dao
interface ObjectiveDao {

    @Query("SELECT * FROM ObjectiveEntity")
    fun getAll(): LiveData<List<ObjectiveEntity>>

    @Insert
    suspend fun insert(objectiveEntity: ObjectiveEntity)

}