package ru.kor.seal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kor.seal.dto.Objective
import ru.kor.seal.dto.Stage
import ru.kor.seal.model.ObjectiveModel
import ru.kor.seal.repository.RepositoryImpl
import javax.inject.Inject

val emptyObjective = Objective(0, "", "")

@HiltViewModel
class ObjectiveViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    val data = repository.data

    private var _dataOpenObjective = MutableLiveData<ObjectiveModel?>()
    val dataOpenObjective: LiveData<ObjectiveModel?> = _dataOpenObjective

    private var _dataEditObjective = MutableLiveData(emptyObjective)
    val dataEditObjective: LiveData<Objective> = _dataEditObjective

    fun saveObjective(name: String, description: String) {
        viewModelScope.launch {
            if (dataEditObjective.value?.id == 0L) {
                repository.saveObjective(Objective(0, name, description))
            } else {
                dataEditObjective.value?.let {
                    repository.saveObjective(it.copy(name = name, description = description))
                }
            }
            _dataEditObjective.value = emptyObjective
        }
    }

    fun removeObjective(objective: Objective) {
        viewModelScope.launch {
            repository.removeObjective(objective)
            repository.removeStageByObjectiveId(objective.id)
        }
    }

    fun setOpenObjective(objective: ObjectiveModel) {
        _dataOpenObjective.value = objective
    }

    fun setEditObjective(objective: Objective) {
        _dataEditObjective.value = objective
    }

    fun resetOpenObjective() {
        _dataOpenObjective.value = null
    }

    fun saveStage(text: String) {
        viewModelScope.launch {
            repository.saveStage(
                Stage(
                    id = 0,
                    objectiveId = dataOpenObjective.value!!.objective.id,
                    text = text
                )
            )
        }
    }

    fun setFinishedStage(stage: Stage) {
        viewModelScope.launch {
            repository.setFinishedStage(stage)
        }
    }

    fun removeStage(stage: Stage) {
        viewModelScope.launch {
            repository.removeStage(stage)
        }
    }

    fun resetEditObjective() {
        _dataEditObjective.value = emptyObjective
    }

}