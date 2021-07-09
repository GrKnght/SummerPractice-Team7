package summerpractice.team7.mymemory.db

import androidx.room.Database
import androidx.room.RoomDatabase
import summerpractice.team7.mymemory.db.dao.AchievementsDao
import summerpractice.team7.mymemory.db.dao.TasksDao

@Database(
    entities = [
        TasksDao::class,
        AchievementsDao::class
    ],
    version = 1
)
abstract class MyMEMoryDB : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun achievementDao(): AchievementsDao
}