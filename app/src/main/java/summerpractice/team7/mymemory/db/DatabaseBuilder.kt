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
        val userAchievementsCount = achievementDao.count()
        val defaultAchievementsCount = AchievementHelper(achievementDao).defaultAchievementListCount()
        when {
            userAchievementsCount == 0 -> {
                AchievementHelper(achievementDao).initializeDB()
            }
            userAchievementsCount != defaultAchievementsCount -> {
                Log.d("DatabaseBuilder.initDB","User has different achievements, reinitializing...")
                Log.d("DatabaseBuilder.initDB","User: $userAchievementsCount | Default: $defaultAchievementsCount")
                val unlockedTasks = AchievementHelper(achievementDao).getUnlocked()
                achievementDao.destroyTable()
                AchievementHelper(achievementDao).initializeDB()
                for (task in unlockedTasks) {
                    try {
                        achievementDao.unlockById(task.id, task.unlockedAt)
                        Log.d("DatabaseBuilder.initDB","- Migrated task id ${task.id}")
                    } catch(e: Exception) {
                        Log.d("DatabaseBuilder.initDB","- Ignored task id ${task.id} (no longed exists)")
                    }
                }
                Log.d("DatabaseBuilder.initDB","Successfully migrated achievements table")
            }
            else -> {
                Log.d("DatabaseBuilder.initDB","No achievement migration needed")
            }
        }
        return db
    }
}