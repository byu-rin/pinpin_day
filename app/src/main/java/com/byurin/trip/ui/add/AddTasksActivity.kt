package com.byurin.trip.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
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

        fun showDatePicker(textView: TextView) {
            val datePickerDialog = DatePickerDialog(this, null, viewModel.year, viewModel.month, viewModel.day)
            // datePickerDialog 의 header 숨김
            datePickerDialog.datePicker.findViewById<View>(resources.getIdentifier("date_picker_header", "id", "android"))?.visibility = View.GONE

            // DatePickerDialog 에서 날짜 선택 시 호출되는 콜백 설정
            datePickerDialog.datePicker.init(viewModel.year, viewModel.month, viewModel.day) { view, year, monthOfYear, dayOfMonth ->
                // LiveData 를 통해 선택된 날짜를 Observer 에게 전달
                viewModel.setSelectedDate(year, monthOfYear, dayOfMonth)
                // 선택된 날짜를 tv 에 반영
                textView.text = "${monthOfYear + 1}월 ${dayOfMonth}일"
                // 날짜 선택 후 dialog 닫기
                datePickerDialog.dismiss()
            }
            datePickerDialog.show()
        }

        // 시작 날짜 클릭 시 DataPickerDialog 보이기
        binding.taskStartDate.setOnClickListener {
            showDatePicker(binding.taskStartDate)
        }

        // 종료 날짜 클릭 시 DataPickerDialog 보이기
        binding.taskEndDate.setOnClickListener {
            showDatePicker(binding.taskEndDate)
        }

        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            // TimePicker 보이기
            binding.timePicker.visibility = View.VISIBLE
        }

    }

    // datePickerDialog 보이기 함수

}
