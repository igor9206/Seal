package ru.kor.seal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.kor.seal.dao.ObjectiveDao
import ru.kor.seal.dao.StageDao
import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage
import ru.kor.seal.entity.toDto
import ru.kor.seal.entity.toEntity
import ru.kor.seal.model.ObjectiveModel
import ru.kor.seal.model.toDto
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val objectiveDao: ObjectiveDao,
    private val stageDao: StageDao,
) : Repository {

    override val data: LiveData<List<ObjectiveModel>> =
        objectiveDao.getAllObjectiveWithStage().map { list ->
            list.map {
                ObjectiveModel(
                    it.objective.toDto(),
                    it.stages.toDto()
                )
            }
        }

    override suspend fun saveObjective(objective: Objective) {
        objectiveDao.insert(objective.toEntity())
    }

    override suspend fun removeObjective(objective: Objective) {
        objectiveDao.removeById(objective.toEntity())
    }

    override suspend fun saveStage(stage: Stage) {
        stageDao.insert(stage.toEntity())
    }

    override suspend fun removeStageByObjectiveId(id: Long) {
        stageDao.removeByObjectiveId(id)
    }

}
