package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage
import ru.kor.seal.model.ObjectiveModel

interface Repository {
    val data: LiveData<List<ObjectiveModel>>

    suspend fun saveObjective(objective: Objective)
    suspend fun removeObjective(objective: Objective)
    suspend fun saveStage(stage: Stage)
    suspend fun removeStageByObjectiveId(id: Long)
}