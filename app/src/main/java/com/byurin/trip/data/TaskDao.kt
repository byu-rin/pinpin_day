package com.byurin.trip.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTasks(searchQuery: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task) // suspend 없는 이유 : Room 라이브러리가 이미 스레드에서 실행되어서

    @Update
    fun update(task: Task) // 이하동문

    @Delete
    fun delete(task: Task) // 이하동문
}