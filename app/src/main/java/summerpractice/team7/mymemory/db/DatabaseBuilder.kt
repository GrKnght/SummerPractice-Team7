package summerpractice.team7.mymemory.db

import android.app.Activity
import androidx.room.*
import android.util.Log
import summerpractice.team7.mymemory.util.AchievementHelper

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

    fun initDB(activity: Activity): MyMEMoryDB {
        val db = createDBInstance(activity)
        val achievementDao = db.achievementDao()
        val userAchievementsCount = achievementDao.getAll().count()
        val defaultAchievementsCount = AchievementHelper(achievementDao).defaultAchievementListCount()
        if (userAchievementsCount == 0) {
            AchievementHelper(achievementDao).initializeDB()
        } else if (userAchievementsCount != defaultAchievementsCount) {
            Log.d("MainActivity.initDB","User has different achievements, reinitializing...")
            Log.d("MainActivity.initDB","User: $userAchievementsCount | Default: $defaultAchievementsCount")
            val unlockedTasks = AchievementHelper(achievementDao).getUnlocked()
            achievementDao.destroyTable()
            AchievementHelper(achievementDao).initializeDB()
            for (task in unlockedTasks) {
                try {
                    achievementDao.unlockById(task.id, task.unlockedAt)
                    Log.d("MainActivity.initDB","- Migrated task id ${task.id}")
                } catch(e: Exception) {
                    Log.d("MainActivity.initDB","- Ignored task id ${task.id} (no longed exists)")
                }
            }
            Log.d("MainActivity.initDB","Successfully migrated achievements table")
        } else {
            Log.d("MainActivity.initDB","No achievement migration needed")
        }
        return db
    }
}