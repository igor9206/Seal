package ru.kor.seal.model

import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage

data class ObjectiveModel(
    val objective: Objective,
    val stages: List<Stage>
)