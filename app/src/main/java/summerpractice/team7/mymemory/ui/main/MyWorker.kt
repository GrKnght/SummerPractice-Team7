package summerpractice.team7.mymemory

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import summerpractice.team7.mymemory.db.MyMEMoryDB
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.dao.AchievementsDao

import summerpractice.team7.mymemory.model.TaskModel
import java.util.*

class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork() : Result {

        return Result.success()
    }

    fun onStart(db: MyMEMoryDB) {

        val id = CreateTaskFragment().id
        val task = db.tasksDao().get(id)
        val startTime = db.tasksDao().get(id).start_date
        val endTime = db.tasksDao().get(id).end_date
        var currentTime = Calendar.getInstance().timeInMillis
        db.tasksDao().update(task)

        if (currentTime != endTime){
            db.tasksDao().updateStatus(id, TasksDao.TaskStatus.Finished)
            val newAchievement = db.achievementDao().unlockRandom().id
            db.achievementDao().unlockById(newAchievement)
        } else {
            db.tasksDao().updateStatus(id, TasksDao.TaskStatus.Failed)
            val deleteAchievement = db.achievementDao().getRandomUnlocked().id

        }
    }
}
