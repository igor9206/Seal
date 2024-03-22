package ru.kor.seal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.kor.seal.entity.StageEntity

@Dao
interface StageDao {

    @Query("SELECT * FROM StageEntity WHERE objectiveId = :id ORDER BY id ASC")
    suspend fun getById(id: Long): List<StageEntity>

    @Insert
    suspend fun insert(stageEntity: StageEntity)

    @Delete
    suspend fun remove(stageEntity: StageEntity)

    @Query("DELETE FROM StageEntity WHERE objectiveId = :id")
    suspend fun removeByObjectiveId(id: Long)

}