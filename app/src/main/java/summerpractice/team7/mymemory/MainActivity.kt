package summerpractice.team7.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import summerpractice.team7.mymemory.fragments.AchieveFragment
import summerpractice.team7.mymemory.fragments.AllTasksFragment
import summerpractice.team7.mymemory.fragments.DaylyTaskFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val achieveFragment = AchieveFragment()
        val allTasksFragment = AllTasksFragment()
        val daylyTaskFragment = DaylyTaskFragment()
        val bottom1: BottomNavigationView = findViewById(R.id.bottom1)

        makeAchieveFragment(achieveFragment)

        bottom1.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dayly_tasks_icon -> makeDaylyTasksFragment(daylyTaskFragment)
                R.id.achievement_icon -> makeAchieveFragment(achieveFragment)
                R.id.all_tasks_icon -> makeAllTaskFragment(allTasksFragment)
            }
            true
        }

    }

    private fun makeAchieveFragment(fragment: AchieveFragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    private fun makeAllTaskFragment(fragment: AllTasksFragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    private fun makeDaylyTasksFragment(fragment: DaylyTaskFragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}