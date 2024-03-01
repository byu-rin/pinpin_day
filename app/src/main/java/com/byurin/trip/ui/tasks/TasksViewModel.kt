package com.byurin.trip.ui.tasks

import androidx.lifecycle.ViewModel
import com.byurin.trip.data.TaskDao
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

}