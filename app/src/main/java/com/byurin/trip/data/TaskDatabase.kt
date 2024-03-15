package com.byurin.trip.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.byurin.trip.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("병원가기"))
                dao.insert(Task("에스테틱 방문"))
                dao.insert(Task("애플 주식 매수", important = true))
                dao.insert(Task("매매일지 작성하기", completed = true))
                dao.insert(Task("생수 구매"))
                dao.insert(Task("당근 거래", completed = true))
                dao.insert(Task("자전거 수리"))
                dao.insert(Task("서점 방문"))
            }
        }
    }
}