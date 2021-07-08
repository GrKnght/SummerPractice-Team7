package summerpractice.team7.mymemory

import android.app.Activity
import androidx.room.*
import summerpractice.team7.mymemory.dao.AchievementsDao
import summerpractice.team7.mymemory.dao.TasksDao
import summerpractice.team7.mymemory.entity.TaskEntity

class DatabaseBuilder {
    fun createDBInstance(activity: Activity): AppDatabase { // TaskDao AchievementDao
        return Room.databaseBuilder(
            activity,
            AppDatabase::class.java, "mymemory.db"
        ).build()
    }

    @Database(entities = [TasksDao::class, AchievementsDao::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun tasksDao(): TasksDao
        abstract fun achievementDao(): AchievementsDao
    }

    // Примеры:
    // val db = createDBInstance(MainActivity)
    // val taskDao = db.tasksDao()
    // val inProgressTasks: List<Task> = taskDao.getTasksWithStatus(TaskStatus.InProgress)
    // val tasks: List<Task> = taskDao.getAll()
    // val updatedTasks: List<Task> = taskDao.updateOutdatedStatuses()
}