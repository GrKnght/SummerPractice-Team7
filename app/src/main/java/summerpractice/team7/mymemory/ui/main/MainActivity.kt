package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.MyWorker
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding
import summerpractice.team7.mymemory.db.DatabaseBuilder
import summerpractice.team7.mymemory.db.MyMEMoryDB
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    private var startTask: Button? = null
    private var finishTask: Button? = null

    lateinit var db: MyMEMoryDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()
        db = DatabaseBuilder().initDB(this)
        finishTask?.visibility = View.GONE


        //val myWorker = MyWorker()

        /*if (startTask !== null) {
            MyWorker.onStart()
            finishTask!!.visibility = View.VISIBLE
            startTask!!.visibility = View.GONE


            if (finishTask == null) {
                finishTask = findViewById(R.id.addingTask)
                finishTask?.setOnClickListener{
                    MyWorker.onSuccess()
                    finishTask!!.visibility = View.GONE
                    startTask!!.visibility = View.VISIBLE
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
        startTask = view.findViewById(R.id.startTask)
        finishTask = view.findViewById(R.id.finishTask)
    }

}


