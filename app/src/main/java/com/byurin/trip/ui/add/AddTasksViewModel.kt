package com.byurin.trip.ui.add

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTasksViewModel : ViewModel() {

    // Calendar 객체 생성하여 디바이스에서 날짜 가져오기
    val cal = Calendar.getInstance()

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
}