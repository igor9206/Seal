package ru.kor.seal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.kor.seal.dto.Objective
import ru.kor.seal.repository.RepositoryImpl
import javax.inject.Inject

@HiltViewModel
class ObjectiveViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    val dataObjectives = repository.data

    private var _dataOpenObjective = MutableLiveData<Objective?>()
    val dataOpenObjective: LiveData<Objective?> = _dataOpenObjective

    fun saveObjective(name: String, description: String) {
        viewModelScope.launch {
            repository.saveObjective(Objective(0, name, description))
        }
    }

    fun removeObjective(objective: Objective) {
        viewModelScope.launch {
            repository.removeObjective(objective)
        }
    }

    fun setOpenObjective(objective: Objective) {
        _dataOpenObjective.value = objective
    }

    fun resetOpenObjective() {
        _dataOpenObjective.value = null
    }

}