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
    val currentHour = currentDate.get(Calendar.HOUR_OF_DAY)
    val currentMinute = currentDate.get(Calendar.MINUTE)
    private val _selectedDate = MutableLiveData(currentDate.time)

    // SimpleDateFormat 초기화하여 날짜 표시하기 (월,일,요일)
    private val dateFormat = SimpleDateFormat("M월 d일 (E)", Locale.getDefault())

    // 현재 날짜 반환 함수
    fun getCurrentDate(): String {
        return dateFormat.format(currentDate.time)
    }

    // 선택된 날짜 설정
    fun setSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        currentDate.set(year, month, dayOfMonth)
        _selectedDate.value = currentDate.time
    }

    // 선택된 시작 및 종료 시간
    private val _startTime = MutableLiveData<String>("00:00")
    val startTime: LiveData<String>
        get() = _startTime

//    private val _endTime = MutableLiveData<String>("00:00")
//    val endTime: LiveData<String>
//        get() = _endTime

    // 시작 시간 업데이트
    fun setStartTime(hour: Int, minute: Int) {
        // 선택한 시간과 분을 형식에 맞춰서 LiveData 에 저장
        _startTime.value = String.format("%02d:%02d", hour, minute)
    }

//    // 종료 시간 업데이트
//    fun setEndTime(hour: Int, minute: Int) {
//        _endTime.value = String.format("%02d:%02d", hour, minute)
//    }
}

/* // DatePickerDialog 에서 날짜 선택 시 호출되는 콜백 설정
            datePickerDialog.datePicker.init(viewModel.year, viewModel.month, viewModel.day) { view, year, monthOfYear, dayOfMonth ->
                // LiveData 를 통해 선택된 날짜를 Observer 에게 전달
                viewModel.setSelectedDate(year, monthOfYear, dayOfMonth)
                // 선택된 날짜를 tv 에 반영
                textView.text = "${monthOfYear + 1}월 ${dayOfMonth}일"
                // 날짜 선택 후 dialog 닫기
                datePickerDialog.dismiss()
            }
            datePickerDialog.show() */