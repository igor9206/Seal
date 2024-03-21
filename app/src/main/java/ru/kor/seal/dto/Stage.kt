package ru.kor.seal.dto

import android.content.IntentSender.OnFinished

data class Stage(
    val id: Long,
    val objectiveId: Long,
    val finished: Boolean = false,
    val text: String
)
