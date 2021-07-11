package summerpractice.team7.mymemory.ui.main.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.UniversalTimeScale.toLong
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentCreateTaskBinding
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.model.TaskModel
import summerpractice.team7.mymemory.ui.base.BaseFragment
import summerpractice.team7.mymemory.ui.main.MainActivity
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_create_task

    private var binding: FragmentCreateTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            with(it) {
                saveTaskBtn.setOnClickListener {
                    var result: TaskEntity = TaskEntity(
                        id = 0,
                        name = binding?.taskNameEt?.text.toString(),
                        description = binding?.taskDescriptionEt?.text.toString(),
                        time_hours = 2,
                        time_minutes = 3,
                        status = TasksDao.TaskStatus.InProgress
                    )
                    // TODO Сохранять таск в БД
                    (requireActivity() as MainActivity).db.tasksDao().add(result)
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment,
                            DailyTaskFragment.getInstance(result),
                            "DAILY_TASK_FRAGMENT"
                        )
                        .commit()
                }

            }
        }
        setUpStartDate()
    }
    private fun setUpStartDate() {
        var result: Long? = null
        binding?.btnStartTime?.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding?.btnStartTime?.text = "Начало: ${getTimeInstance().format(cal.time)}"
            }
            val tpd = TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

            /*val dpd = DatePickerDialog(
                activity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

                },
                year,
                month,
                day
            )*/
        }
    }
    private fun setUpEndDate() {
        var result: Long? = null
        binding?.btnEndTime?.setOnClickListener {
            /*val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

                },
                year,
                month,
                day
            )

            dpd.show()*/
        }
    }
}