package com.byurin.trip.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table") // Room DB에서 해당 클래스를 테이블로 사용하도록 지정한다.
@Parcelize
data class Task(
    val name : String, // Task 이름
    val important : Boolean = false, // Task 중요도 (기본값은 false)
    val completed : Boolean = false, // Task 완료 여부 (기본값은 false)
    val created: Long = System.currentTimeMillis(), // Task 생성된 시간(기본값은 현재 시간)
    @PrimaryKey(autoGenerate = true) val id: Int = 0 // Task 고유 식별자 (자동 생성, 기본값 = 0)
) : Parcelable {
    val createdDateFormatted: String // Task 가 생성된 시간을 포맷팅하여 반환
        get() = DateFormat.getDateTimeInstance().format(created) // 생성된 시간을 포맷팅하여 문자열로 반환
}
