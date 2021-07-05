package summerpractice.team7.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import summerpractice.team7.mymemory.fragments.AchieveFragment
import summerpractice.team7.mymemory.fragments.AllTasksFragment
import summerpractice.team7.mymemory.fragments.DaylyTaskFragment

class MainActivity : AppCompatActivity() {

    var smthHas: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Все фрагменты
        val achieveFragment = AchieveFragment() as Fragment
        val allTasksFragment = AllTasksFragment() as Fragment
        val daylyTaskFragment = DaylyTaskFragment() as Fragment
        val bottom1: BottomNavigationView = findViewById(R.id.bottom1)

        //Атрибуты, включащиеся, когда во фрагменте ничего нет
        val smthNoText: TextView = findViewById(R.id.smthNoText)
        val smthNoIcon: ImageView = findViewById(R.id.noTasksIcon)

        makeFragment(daylyTaskFragment)
        if(smthHas) {
            smthNoIcon.visibility = View.GONE
            smthNoText.visibility = View.GONE
        }
        smthNoText.setText("У вас сегодня нет дел")

        bottom1.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dayly_tasks_icon -> {
                    makeFragment(daylyTaskFragment)
                    smthNoText.setText("У вас сегодня нет дел")
                }
                R.id.achievement_icon -> {
                    makeFragment(achieveFragment)
                    smthNoText.setText("У вас нет достижений")
                }
                R.id.all_tasks_icon -> {
                    makeFragment(allTasksFragment)
                    smthNoText.setText("У вас нет списка задач")
                }
            }
            true
        }

    }

    private fun makeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}