package summerpractice.team7.mymemory

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import summerpractice.team7.mymemory.db.MyMEMoryDB
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.dao.AchievementsDao

import summerpractice.team7.mymemory.model.TaskModel
import summerpractice.team7.mymemory.ui.main.fragment.CreateTaskFragment
import java.util.*
import java.util.concurrent.TimeUnit

class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {


    override fun doWork(): Result {

        fun onStart(db: MyMEMoryDB, /*значение в миллисекундах, введное польхователей*/) {

            val id = 0
            val task = db.tasksDao().get(id)
            db.tasksDao().updateStatus(id,TasksDao.TaskStatus.InProgress)

            //В startTime прописывается время на выполнение задачи в минутах

            //val startTime = db.tasksDao().get(id)

            //val endTime = db.tasksDao().get(id).end_date
            //val millisecond = startTime?.let { TimeUnit.MINUTES.convert(it,TimeUnit.MILLISECONDS) }
            //val endTime = Calendar.getInstance().timeInMillis + millisecond!!
            //db.tasksDao().update(task)

            var currentTime = Calendar.getInstance().timeInMillis

            /*if (currentTime != endTime) {
                db.tasksDao().updateStatus(id, TasksDao.TaskStatus.Finished)
                db.achievementDao().unlockRandom().id
            } else {
                db.tasksDao().updateStatus(id, TasksDao.TaskStatus.Failed)
                db.achievementDao().getRandomUnlocked().id
            }*/
        }
        return Result.success()
    }
}