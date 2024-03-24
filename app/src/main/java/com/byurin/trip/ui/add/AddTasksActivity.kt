package com.byurin.trip.ui.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        // LiveData 를 통해 선택된 날짜를 Observer 에게 전달
        viewModel.startTime.observe(this, Observer { startTime ->
            // LiveData 값 변경될 때마다 해당 tv 업데이트
            binding.taskStartTime.text = startTime
        })

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

        // 시작 시간 클릭 시 TimePickerDialog 보이기
        binding.taskStartTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // TODO(textview 업데이트 안됨. LiveData 문제인지 Binding 문제인지 확인 필요)
                    // TODO(디버깅 학습)
                    // 사용자가 선택한 시간과 분을 LiveData 에 업데이트
                    viewModel.setStartTime(hourOfDay, minute)
                    // LiveData 값이 변경되었으므로 해당 TextView를 업데이트
                    binding.taskStartTime.text = "${hourOfDay}:${minute}"
                },
                viewModel.currentHour,
                viewModel.currentMinute,
                true
            )
            // TimePickerDialog 보이기
            timePickerDialog.show()
        }
    }
}
