package com.byurin.trip.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

@Entity(tableName = "task_table") // Room DB에서 해당 클래스를 테이블로 사용하도록 지정한다.
@Parcelize
data class Task(
    val name : String, // Task 이름
    val startTimeMain: Long, // Task 시작 시간 (시간 단위)
    val startTimeInMillis: Long, // Task 시작 시간 (밀리초 단위)
    val endTimeInMillis: Long, // Task 종료 시간 (밀리초 단위)
    val important : Boolean = false, // Task 중요도 (기본값은 false)
    val created: Long = System.currentTimeMillis(), // Task 생성된 시간(기본값은 현재 시간)
    @PrimaryKey(autoGenerate = true) val id: Int = 0 // Task 고유 식별자 (자동 생성, 기본값 = 0)
) : Parcelable {

    fun getFormattedTimeRange(): String { // Task 시작 시간을 포맷팅하여 반환
        val startTime = Calendar.getInstance().apply {
            timeInMillis = startTimeInMillis
        }
        val endTime = Calendar.getInstance().apply {
            timeInMillis = endTimeInMillis
        }

        val startTimeFormat = SimpleDateFormat("HH시 mm분")
        val endTimeFormat = SimpleDateFormat("HH시 mm분")

        return "${startTimeFormat.format(startTime.time)} - ${endTimeFormat.format(endTime.time)}" // 시작 시간 - 종료 시간 형태로 반환
    }

    fun getFormattedTimeMain(): String { // Task 시작 시간을 포맷팅하여 반환
        val startTime = Calendar.getInstance().apply {
            timeInMillis = startTimeMain
        }

        val startTimeFormat = SimpleDateFormat("HH:mm")

        return "${startTimeFormat.format(startTime.time)}" // 시작 시간 형태로 반환
    }
    val createdDateFormatted: String // Task 가 생성된 시간을 포맷팅하여 반환
        get() = DateFormat.getDateTimeInstance().format(created) // 생성된 시간을 포맷팅하여 문자열로 반환
}
