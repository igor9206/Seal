package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import ru.kor.seal.dto.Objective

interface Repository {
    val data: LiveData<List<Objective>>

    suspend fun saveObjective(objective: Objective)
    suspend fun removeObjective(objective: Objective)
}