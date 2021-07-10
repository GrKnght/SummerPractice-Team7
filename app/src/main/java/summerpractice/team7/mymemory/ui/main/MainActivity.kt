package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding
import summerpractice.team7.mymemory.db.DatabaseBuilder
import summerpractice.team7.mymemory.db.MyMEMoryDB

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    lateinit var db: MyMEMoryDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()
        db = DatabaseBuilder().createDBInstance(this)


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

}


