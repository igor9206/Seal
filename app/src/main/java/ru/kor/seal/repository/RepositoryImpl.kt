package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.kor.seal.dao.ObjectiveDao
import ru.kor.seal.dao.StageDao
import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage
import ru.kor.seal.entity.StageEntity
import ru.kor.seal.entity.toDto
import ru.kor.seal.entity.toEntity
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val objectiveDao: ObjectiveDao,
    private val stageDao: StageDao,
) : Repository {

    override val dataObjective: LiveData<List<Objective>> = objectiveDao.getAll().map { list ->
        list.map { it.toDto() }
    }

    private val _dataStage = MutableLiveData<List<Stage>>()
    override val dataStage: LiveData<List<Stage>> = _dataStage

    override suspend fun saveObjective(objective: Objective) {
        objectiveDao.insert(objective.toEntity())
    }

    override suspend fun removeObjective(objective: Objective) {
        objectiveDao.removeById(objective.toEntity())
    }

    override suspend fun getStage(id: Long) {
        val list = stageDao.getById(id).map { stageEntity ->
            stageEntity.toDto()
        }
        _dataStage.value = list
    }

    override suspend fun saveStage(stage: Stage) {
        stageDao.insert(stage.toEntity())
        getStage(stage.objectiveId)
    }

}
