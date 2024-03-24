package com.byurin.trip.ui.tasks

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.byurin.trip.R
import com.byurin.trip.databinding.ActivityAddTasksBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 현재 날짜로 데이터 초기화
        setupCurrentDate()
    }

    // 시작 날짜와 현재 날짜를 디바이스 현재 날짜로 셋팅
    private fun setupCurrentDate() {
        val currrentDate = getCurrentDate()
        binding.taskStartDate.text = currrentDate
        binding.taskEndDate.text = currrentDate
    }
    // 디바이스 캘린더에서 현재 날짜 가져오기
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("M월 d일 (E)", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    override fun onResume() {
        super.onResume()
//        enableEdgeToEdge()
        binding.taskStartDate.setOnClickListener {


            // 현재 날짜 가져오기
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            // DatePicker Header 없애기
            val datePickerDialog = DatePickerDialog(this, null, year, month, day)
            datePickerDialog.datePicker.findViewById<View>(resources.getIdentifier("date_picker_header", "id", "android"))?.visibility = View.GONE
            datePickerDialog.show()
//            binding.datePicker.visibility = View.VISIBLE
        }

        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            // TimePicker 보이기
            binding.timePicker.visibility = View.VISIBLE
        }
    }


}
