package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.kor.seal.dao.ObjectiveDao
import ru.kor.seal.dto.Objective
import ru.kor.seal.entity.toDto
import ru.kor.seal.entity.toEntity
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val objectiveDao: ObjectiveDao
) : Repository {

    override val data: LiveData<List<Objective>> = objectiveDao.getAll().map { list ->
        list.map { it.toDto() }
    }

    override suspend fun saveObjective(objective: Objective) {
        objectiveDao.insert(objective.toEntity())
    }

    override suspend fun removeObjective(objective: Objective) {
        objectiveDao.removeById(objective.toEntity())
    }

}
