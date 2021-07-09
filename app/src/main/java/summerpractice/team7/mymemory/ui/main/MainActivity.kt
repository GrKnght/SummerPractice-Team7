package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initNavController()
    }

    private fun initNavController() {
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment)
                .navController
        binding?.bottomNavigationView?.setupWithNavController(navController)
    }


    /*DatabaseBuilder().createDBInstance(this)

       val task: Task = Task()
       .status
       val currentTime = Calendar.getInstance().timeInMillis
       val estimated = currentTIme + считанное из бд время на задачу
       TasksDao.update(task)
       val task = TasksDao.get(id).endDate

       estimated - currentTime != 0 -> Задача выполняется
       + пользователь нажать на задачку и сказать, что он выполнил
           -> Обновляешь статус задачи в БД (таблица tasks)
           -> Достаешь список неоткрытых достижеий (если есть)
           -> Обновляешь статус случайного достижения из списка
       else -> Задание провалено
               -> Обновляешь статус задачи в БД (таблица tasks)
               -> В таблице достижений ты удаляешь одно случайное, если оно есть у пользователя
                   -> Достаешь ачивку
                   -> AchievementDao.update(ачивку)
                   -> Завершаешь работу сервиса/ ворк менеджера*/
}