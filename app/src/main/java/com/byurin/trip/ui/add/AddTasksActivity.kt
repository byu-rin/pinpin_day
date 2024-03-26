package com.byurin.trip.ui.add

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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

        // LiveData 를 통해 선택된 시작 시간을 Observer 에게 전달
        viewModel.startTime.observe(this, Observer { startTime ->
            // LiveData 값 변경될 때마다 해당 tv 업데이트
            binding.taskStartTime.text = startTime
        })

        // LiveData 를 통해 선택된 종료 시간을 Observer 에게 전달
        viewModel.endTime.observe(this, Observer { endTime ->
            // LiveData 값 변경될 때마다 해당 tv 업데이트
            binding.taskEndTime.text = endTime
        })

        // 현재 날짜와 시간으로 데이터 초기화
        setupCurrentDate()


        // 시작 날짜 클릭 시 DatePickerDialog 보이기
        binding.taskStartDate.setOnClickListener {
            showDatePicker(binding.taskStartDate)
        }

        // 종료 날짜 클릭 시 DatePickerDialog 보이기
        binding.taskEndDate.setOnClickListener {
            showDatePicker(binding.taskEndDate)
        }

        // 시작 시간 클릭 시 TimePickerDialog 보이기
        binding.taskStartTime.setOnClickListener {
            showStartCustomTimePicker()
        }

        // 종료 시간 클릭 시 TimePickerDialog 보이기
        binding.taskEndTime.setOnClickListener {
            showEndCustomTimePicker()
        }
    }

    // 시작 날짜와 현재 날짜를 디바이스 현재 날짜로 셋팅
    private fun setupCurrentDate() {
        // ViewModel 에서 현재 날짜 가져오기
        val currrentDate = viewModel.getCurrentDate()
        binding.taskStartDate.text = currrentDate
        binding.taskEndDate.text = currrentDate

    }

    // 시간 클릭 시 custom timepicker 를 보여주는 함수
    fun showStartCustomTimePicker() {
        // custom TimePicker를 포함하는 레이아웃을 inflate
        val customTimePickerView = layoutInflater.inflate(R.layout.custom_time_picker, null)

        // custom TimePicker의 버튼 및 TimePicker를 참조
        val timePicker = customTimePickerView.findViewById<TimePicker>(R.id.time_picker)
        val setButton = customTimePickerView.findViewById<Button>(R.id.time_btn_yes)

        // AlertDialog를 생성하고 custom TimePicker를 설정
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(customTimePickerView)
        val dialog = dialogBuilder.create()

        // "set" 버튼 클릭 시 처리
        setButton.setOnClickListener {
            // 선택된 시간을 가져와서 처리할 수 있습니다.
            val hour = timePicker.hour
            val minute = timePicker.minute
            // 선택한 시간을 TextView에 표시
            viewModel.setStartTime(hour, minute)
//            viewModel.setEndTime(hour, minute)

            // 다 처리한 후에는 다이얼로그를 닫습니다.
            dialog.dismiss()
        }

//        // LiveData 변경 감지를 위해 Observer 설정
//        timeLiveData.observe(this, Observer { time ->
//            // LiveData 값이 변경될 때마다 TimePicker를 업데이트합니다.
//            val timeSplit = time.split(":")
//            val hour = timeSplit[0].toInt()
//            val minute = timeSplit[1].toInt()
//            timePicker.hour = hour
//            timePicker.minute = minute
//        })

        // 다이얼로그를 표시합니다.
        dialog.show()
    }

    fun showEndCustomTimePicker() {
        // custom TimePicker를 포함하는 레이아웃을 inflate
        val customTimePickerView = layoutInflater.inflate(R.layout.custom_time_picker, null)

        // custom TimePicker의 버튼 및 TimePicker를 참조
        val timePicker = customTimePickerView.findViewById<TimePicker>(R.id.time_picker)
        val setButton = customTimePickerView.findViewById<Button>(R.id.time_btn_yes)

        // AlertDialog를 생성하고 custom TimePicker를 설정
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(customTimePickerView)
        val dialog = dialogBuilder.create()

        // "set" 버튼 클릭 시 처리
        setButton.setOnClickListener {
            // 선택된 시간을 가져와서 처리할 수 있습니다.
            val hour = timePicker.hour
            val minute = timePicker.minute
            // 선택한 시간을 TextView에 표시
//            viewModel.setStartTime(hour, minute)
            viewModel.setEndTime(hour, minute)

            // 다 처리한 후에는 다이얼로그를 닫습니다.
            dialog.dismiss()
        }

//        // LiveData 변경 감지를 위해 Observer 설정
//        timeLiveData.observe(this, Observer { time ->
//            // LiveData 값이 변경될 때마다 TimePicker를 업데이트합니다.
//            val timeSplit = time.split(":")
//            val hour = timeSplit[0].toInt()
//            val minute = timeSplit[1].toInt()
//            timePicker.hour = hour
//            timePicker.minute = minute
//        })

        // 다이얼로그를 표시합니다.
        dialog.show()
    }

//     날짜 클릭 시 DatePickerDialog 를 보여주는 함수
    private fun showDatePicker(textView: TextView) {
        val datePickerDialog =
            DatePickerDialog(this, null, viewModel.year, viewModel.month, viewModel.day)
        // datePickerDialog 의 header 숨김
        datePickerDialog.datePicker.findViewById<View>(
            resources.getIdentifier(
                "date_picker_header",
                "id",
                "android"
            )
        )?.visibility = View.GONE

        // DatePickerDialog 에서 날짜 선택 시 호출되는 콜백 설정
        datePickerDialog.datePicker.init(
            viewModel.year,
            viewModel.month,
            viewModel.day
        ) { view, year, monthOfYear, dayOfMonth ->
            // 선택된 날짜를 tv 에 반영
            textView.text = "${monthOfYear + 1}월 ${dayOfMonth}일"
            // 날짜 선택 후 dialog 닫기
            datePickerDialog.dismiss()
        }
        datePickerDialog.show()
    }

    override fun onResume() {
        super.onResume()

    }
}
