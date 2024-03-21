package ru.kor.seal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kor.seal.dto.Stage

@Entity
data class StageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val objectiveId: Long,
    val finished: Boolean = false,
    val text: String
)

fun StageEntity.toDto() = Stage(id, objectiveId, finished, text)

fun Stage.toEntity() = StageEntity(id, objectiveId, finished, text)