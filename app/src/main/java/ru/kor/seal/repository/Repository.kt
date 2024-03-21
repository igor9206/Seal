package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage

interface Repository {
    val dataObjective: LiveData<List<Objective>>
    val dataStage: LiveData<List<Stage>>

    suspend fun saveObjective(objective: Objective)
    suspend fun removeObjective(objective: Objective)
    suspend fun getStage(id: Long)
    suspend fun saveStage(stage: Stage)
}