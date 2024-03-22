package ru.kor.seal.model

import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage
import ru.kor.seal.entity.StageEntity

data class ObjectiveModel(
    val objective: Objective,
    val stages: List<Stage>
)