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
                dao.insert(Task("visit HarryPorter Studio", 240312, 240312, 10, 11, true))
                dao.insert(Task("have a lunch at the sushi restaurant",240312, 240312, 12, 1))
                dao.insert(Task("get some coffee", 240312, 240312, 2, 3))
                dao.insert(Task("buy some water", 240312, 240312, 3, 4))
                dao.insert(Task("visit to humanmade", 240312, 240312, 4, 5))
                dao.insert(Task("Departure to shibuya", 240312, 240312, 5, 6))
                dao.insert(Task("eat a okonomi-yaki", 240312, 240312, 7, 8))
                dao.insert(Task("playground Fuji-Q Highland", 240312, 240312, 9, 10))
            }
        }
    }
}