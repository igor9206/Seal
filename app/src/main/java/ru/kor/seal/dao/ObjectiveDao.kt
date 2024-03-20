package ru.kor.seal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.kor.seal.entity.ObjectiveEntity

@Dao
interface ObjectiveDao {

    @Query("SELECT * FROM ObjectiveEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<ObjectiveEntity>>

    @Insert
    suspend fun insert(objectiveEntity: ObjectiveEntity)

    @Delete
    suspend fun removeById(objectiveEntity: ObjectiveEntity)

}