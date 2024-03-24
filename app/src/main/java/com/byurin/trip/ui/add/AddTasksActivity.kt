package com.byurin.trip.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.byurin.trip.R
import com.byurin.trip.databinding.ActivityAddTasksBinding

class AddTasksActivity : AppCompatActivity() {
    private val viewModel: AddTasksViewModel by viewModels()
    private lateinit var binding: ActivityAddTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tasks)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 현재 날짜로 데이터 초기화
        setupCurrentDate()
    }

    // 시작 날짜와 현재 날짜를 디바이스 현재 날짜로 셋팅
    private fun setupCurrentDate() {
        // ViewModel 에서 현재 날짜 가져오기
        val currrentDate = viewModel.getCurrentDate()
        binding.taskStartDate.text = currrentDate
        binding.taskEndDate.text = currrentDate
    }

    override fun onResume() {
        super.onResume()
        // 시작 날짜 클릭 시 DataPickerDialog 보이기
        binding.taskStartDate.setOnClickListener {
            showDatePicker()
        }

        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            // TimePicker 보이기
            binding.timePicker.visibility = View.VISIBLE
        }
    }

    // datePickerDialog 보이기 함수
    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(this, null, viewModel.year, viewModel.month, viewModel.day)
        // datePickerDialog 의 header 숨김
        datePickerDialog.datePicker.findViewById<View>(resources.getIdentifier("date_picker_header", "id", "android"))?.visibility = View.GONE
        datePickerDialog.show()
    }
}
