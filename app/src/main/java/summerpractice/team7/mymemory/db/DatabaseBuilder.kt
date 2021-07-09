package summerpractice.team7.mymemory.db

import android.app.Activity
import androidx.room.*

class DatabaseBuilder {

    fun createDBInstance(activity: Activity): MyMEMoryDB { // TaskDao AchievementDao
        return Room.databaseBuilder(activity, MyMEMoryDB::class.java, "mymemory.db")
            .allowMainThreadQueries()
            .build()
    }

    // Примеры:
    // val db = createDBInstance(MainActivity)
    // val taskDao = db.tasksDao()
    // val inProgressTasks: List<Task> = taskDao.getTasksWithStatus(TaskStatus.InProgress)
    // val tasks: List<Task> = taskDao.getAll()
    // val updatedTasks: List<Task> = taskDao.updateOutdatedStatuses()
}