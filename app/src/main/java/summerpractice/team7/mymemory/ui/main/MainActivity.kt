package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.MyWorker
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding
import summerpractice.team7.mymemory.db.DatabaseBuilder
import summerpractice.team7.mymemory.db.MyMEMoryDB

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    private var startButton: Button? = null
    private var finishButton: Button? = null

    lateinit var db: MyMEMoryDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()
        db = DatabaseBuilder().initDB(this)


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

    private fun findView(view: View){
        startButton = view.findViewById(R.id.task_completed)
        finishButton = view.findViewById(R.id.task_failed)
    }

}


