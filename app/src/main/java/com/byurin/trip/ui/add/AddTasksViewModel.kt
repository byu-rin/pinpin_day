package com.byurin.trip.ui.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build.VERSION_CODES.M
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTasksViewModel : ViewModel() {

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _startTime = MutableLiveData<String>()
    val startTime: LiveData<String> = _startTime

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _endTime = MutableLiveData<String>()
    val endTime: LiveData<String> = _endTime

    private val dateFormat = SimpleDateFormat("M월 d일", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    init {
        val currentCalendar = Calendar.getInstance()
        _startDate.value = dateFormat.format(currentCalendar.time)
        _startTime.value = timeFormat.format(currentCalendar.time)
        _endDate.value = dateFormat.format(currentCalendar.time)
        _endTime.value = timeFormat.format(currentCalendar.time)
    }

    fun updateStartDate(year: Int, month: Int, dayOfMonth: Int) {
        /* 원본
        //val calendar = Calendar.getInstance().apply {
        //            set(Calendar.HOUR_OF_DAY, hourOfDay)
        //            set(Calendar.MINUTE, minute)
        */
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
        _startDate.value = dateFormat.format(calendar.time)
    }

    fun updateStartTime(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        _startTime.value = timeFormat.format(calendar.time)
    }
}