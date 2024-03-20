package ru.kor.seal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kor.seal.dto.Objective

@Entity
data class ObjectiveEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String,
)

fun ObjectiveEntity.toDto() = Objective(id, name, description)

fun Objective.toEntity() =
    ObjectiveEntity(id, name, description)