package summerpractice.team7.mymemory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupWithNavController
import summerpractice.team7.mymemory.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)

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
}