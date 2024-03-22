package com.byurin.trip.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.byurin.trip.data.OptionMenu
import com.byurin.trip.data.PreferenceManager
import com.byurin.trip.data.Task
import com.byurin.trip.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val preferenceFlow = preferenceManager.preferenceFlow

    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    fun getTasks(optionMenu: OptionMenu) = viewModelScope.launch {
        taskDao.getTasks(searchQuery.value, optionMenu).collect {
            // 결과 처리
        }
    }


    fun onOptionMenuSelected(optionMenu: OptionMenu) {
        viewModelScope.launch {
            preferenceManager.updateOptionMenu(optionMenu)
            getTasks(optionMenu)
        }
    }
}


//    private val tasksFlow = combine(
//        searchQuery,
//        preferenceFlow
//    ) { query, filterPreferences ->
//        Pair(query, filterPreferences)
//    }.flatMapLatest { (query, filterPreferences) ->
//        taskDao.getTasks(query, optionMenu)
//    }

//    val tasks = tasksFlow.asLiveData()

//    fun onOptionMenuSelected(optionMenu: OptionMenu) = viewModelScope.launch {
//        preferenceManager.updateOptionMenu(optionMenu)
//    }
//
//    fun onTaskSelected(task: Task) {
//    }

//    fun onTaskSwiped(task: Task) = viewModelScope.launch {
//        withContext(Dispatchers.IO) {
//            taskDao.delete(task)
//            tasksEventChannel.send(TasksEvent.ShowUndoDeleteTaskMessage(task))
//        }
//
//    }

//    fun onUndoDeleteClick(task: Task) = viewModelScope.launch {
//        withContext(Dispatchers.IO) {
//            taskDao.insert(task)
//        }
//    }

    sealed class TasksEvent {
        data class ShowUndoDeleteTaskMessage(val task: Task) : TasksEvent()
        object NavigateToAddTaskScreen : TasksEvent()
        object NavigateToEditTaskScreen : TasksEvent()
        data class ShowTaskSavedConfirmationMessage(val msg: String) : TasksEvent()
        data class ShowTaskSavedErrorMessage(val msg: String) : TasksEvent()
    }
//}
