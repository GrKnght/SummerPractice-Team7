package summerpractice.team7.mymemory.db

import androidx.room.Database
import androidx.room.RoomDatabase
import summerpractice.team7.mymemory.db.dao.AchievementsDao
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.AchievementEntity
import summerpractice.team7.mymemory.db.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        AchievementEntity::class
    ],
    version = 1
)
abstract class MyMEMoryDB : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun achievementDao(): AchievementsDao
}