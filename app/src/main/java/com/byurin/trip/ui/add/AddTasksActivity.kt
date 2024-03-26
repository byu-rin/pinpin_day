package com.byurin.trip.ui.add

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.byurin.trip.R
import com.byurin.trip.databinding.ActivityAddTasksBinding

class AddTasksActivity : AppCompatActivity() {
    private val viewModel: AddTasksViewModel by viewModels()
    private lateinit var binding: ActivityAddTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tasks)
        binding.lifecycleOwner = this  // Important for LiveData binding
        binding.viewModel = viewModel

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.startDate.observe(this) { date ->
            binding.taskStartDate.text = date
        }

        viewModel.startTime.observe(this) { time ->
            binding.taskStartTime.text = time
        }

        viewModel.endDate.observe(this) { date ->
            binding.taskEndDate.text = date
        }

        viewModel.endTime.observe(this) { time ->
            binding.taskEndTime.text = time
        }

        binding.taskStartDate.setOnClickListener {
//            showDatePicker { year, month, dayOfMonth ->
//                viewModel.updateStartDate(year, month, dayOfMonth)
//            }
        }

        binding.taskStartTime.setOnClickListener {
            showTimePicker { hour, minute ->
                viewModel.updateStartTime(hour, minute)
            }
        }
    }

    private fun showTimePicker(dataSetListener: (hour: Int, minute: Int) -> Unit) {
//        val timePickerDialog = TimePickerDialog(this, { _, hour, minute ->
    }
}

//private fun showDatePicker(textView: TextView) {
//    val datePickerDialog =
//        DatePickerDialog(this, null, viewModel.year, viewModel.month, viewModel.day)
//    // datePickerDialog 의 header 숨김
//    datePickerDialog.datePicker.findViewById<View>(
//        resources.getIdentifier(
//            "date_picker_header",
//            "id",
//            "android"
//        )
//    )?.visibility = View.GONE
//
//    // DatePickerDialog 에서 날짜 선택 시 호출되는 콜백 설정
//    datePickerDialog.datePicker.init(
//        viewModel.year,
//        viewModel.month,
//        viewModel.day
//    ) { view, year, monthOfYear, dayOfMonth ->
//        // 선택된 날짜를 tv 에 반영
//        textView.text = "${monthOfYear + 1}월 ${dayOfMonth}일"
//        // 날짜 선택 후 dialog 닫기
//        datePickerDialog.dismiss()
//    }
//    datePickerDialog.show()
//}