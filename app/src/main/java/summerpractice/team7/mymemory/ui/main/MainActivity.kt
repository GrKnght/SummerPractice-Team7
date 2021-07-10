package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding
import summerpractice.team7.mymemory.db.DatabaseBuilder
import summerpractice.team7.mymemory.db.MyMEMoryDB
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.util.AchievementHelper

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    lateinit var db: MyMEMoryDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()
        initDB()


        //val myWorker = MyWorker(application Context, )


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

    private fun initDB() {
        db = DatabaseBuilder().createDBInstance(this)
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
    }

}


