package com.byurin.trip.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTasksViewModel : ViewModel() {

    // 디바이스에서 날짜 시간 가져오기
    val currentDate = Calendar.getInstance()
    val year = currentDate.get(Calendar.YEAR)
    val month = currentDate.get(Calendar.MONTH)
    val day = currentDate.get(Calendar.DAY_OF_MONTH)


    // 선택된 시작 시간과 종료 시간을 LiveData로 관리
    private val _startTime = MutableLiveData<String>()
    private val _endTime = MutableLiveData<String>()

    val startTime: LiveData<String>
        get() = _startTime

    val endTime: LiveData<String>
        get() = _endTime

    // SimpleDateFormat 초기화하여 날짜 표시하기 (월,일)
    private val dateFormat = SimpleDateFormat("M월 d일", Locale.getDefault())

    // 현재 날짜 반환 함수
    fun getCurrentDate(): String {
        return dateFormat.format(currentDate.time)
    }

    // 시작 시간 업데이트
    fun setStartTime(hour: Int, minute: Int) {
        // 선택한 시간과 분을 형식에 맞춰서 LiveData 에 저장
        _startTime.value = String.format("%02d:%02d", hour, minute)
    }

    // 종료 시간 업데이트
    fun setEndTime(hour: Int, minute: Int) {
        _endTime.value = String.format("%02d:%02d", hour, minute)
    }
}