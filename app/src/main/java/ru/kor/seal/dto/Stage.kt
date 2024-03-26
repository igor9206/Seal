package ru.kor.seal.dto

data class Stage(
    val id: Long,
    val objectiveId: Long,
    val finished: Boolean = false,
    val text: String
)
