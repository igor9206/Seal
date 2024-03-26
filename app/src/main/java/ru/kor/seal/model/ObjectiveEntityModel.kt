package ru.kor.seal.model

import androidx.room.Embedded
import androidx.room.Relation
import ru.kor.seal.dto.Stage
import ru.kor.seal.entity.ObjectiveEntity
import ru.kor.seal.entity.StageEntity
import ru.kor.seal.entity.toDto

data class ObjectiveEntityModel(
    @Embedded val objective: ObjectiveEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "objectiveId"
    )
    val stages: List<StageEntity>
)

fun List<StageEntity>.toDto(): List<Stage> = map {
    it.toDto()
}