package summerpractice.team7.mymemory

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork() : Result {

        return Result.success()
    }

    /*fun onStart(db: MyMEMoryDB) {

        val task = db.tasksDao().get(id).start_date
        var currentTime = Calendar.getInstance().timeInMillis
        val estimated = currentTime + task.db.start_date()
        db.tasksDao().updateStatus(id)

        if (estimated - currentTime != 0){

        }
    }*/
}
