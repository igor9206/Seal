package ru.kor.seal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.kor.seal.entity.ObjectiveEntity
import ru.kor.seal.model.ObjectiveEntityModel

@Dao
interface ObjectiveDao {
    @Transaction
    @Query("SELECT * FROM ObjectiveEntity ORDER BY id DESC")
    fun getAllObjectiveWithStage(): LiveData<List<ObjectiveEntityModel>>

    @Query("SELECT * FROM ObjectiveEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<ObjectiveEntity>>

    @Insert
    suspend fun insert(objectiveEntity: ObjectiveEntity)

    @Delete
    suspend fun removeById(objectiveEntity: ObjectiveEntity)

}