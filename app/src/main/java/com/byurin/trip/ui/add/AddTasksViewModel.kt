package com.byurin.trip.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.Month
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTasksViewModel : ViewModel() {

    // Calendar 객체 생성하여 디바이스에서 날짜 가져오기
    val cal = Calendar.getInstance()
    private val _selectedDate = MutableLiveData(cal.time)
    val selectedDate: LiveData<Date> get() = _selectedDate

    // 현재 년, 월, 일 가져오기
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val day = cal.get(Calendar.DAY_OF_MONTH)

    // SimpleDateFormat 초기화하여 날짜 표시하기 (월,일,요일)
    private val dateFormat = SimpleDateFormat("M월 d일 (E)", Locale.getDefault())

    // 현재 날짜 반환 함수
    fun getCurrentDate(): String {
        return dateFormat.format(cal.time)
    }

    // 선택된 날짜 설정
    fun setSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        cal.set(year, month, dayOfMonth)
        _selectedDate.value = cal.time
    }
}