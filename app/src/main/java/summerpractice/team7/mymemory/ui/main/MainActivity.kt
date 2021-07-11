package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding
import summerpractice.team7.mymemory.db.DatabaseBuilder
import summerpractice.team7.mymemory.db.MyMEMoryDB
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.TaskEntity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    private var startButton: Button? = null
    private var finishButton: Button? = null


    lateinit var db: MyMEMoryDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseBuilder().initDB(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()

        //val myWorker = MyWorker()

        /*if (startButton !== null) {
            MyWorker.onStart()

            if (finishButton == null) {
                finishButton = findViewById(R.id.addingTask)
                finishButton?.setOnClickListener{
                    MyWorker.onSuccess()
                }
            } else {
                MyWorker.onFailled()
            }
        }*/
    }

    private fun initNavController() {
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment)
                .navController
        binding?.bottomNavigationView?.setupWithNavController(navController)
    }

    private fun findView(view: View) {
        startButton = view.findViewById(R.id.task_completed)
        finishButton = view.findViewById(R.id.task_failed)
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .show()
    }

    fun finishTask(task: TaskEntity) {
        db.tasksDao().updateStatus(task.id, TasksDao.TaskStatus.Finished)
        val locked = db.achievementDao().getRandomLocked()
        db.achievementDao().unlockById(locked.id, Calendar.getInstance().timeInMillis)
        showAlertDialog("Поздравляем", "Вы успешно завершили ${task.name}\nРазблокировано достижение \"${locked.name}\"")
    }
}


