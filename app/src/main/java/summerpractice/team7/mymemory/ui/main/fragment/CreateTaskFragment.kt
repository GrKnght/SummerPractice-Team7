package summerpractice.team7.mymemory.ui.main.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentCreateTaskBinding
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.ui.base.BaseFragment
import summerpractice.team7.mymemory.ui.main.MainActivity
import java.text.DateFormat.getTimeInstance
import java.util.*

class CreateTaskFragment : BaseFragment() {

    private val selectedTime: Calendar = Calendar.getInstance()

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
                        start_date = System.currentTimeMillis() / 1000L,
                        end_date = selectedTime.timeInMillis / 1000L,
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
        binding?.btnStartTime?.setOnClickListener {
            println("TEST TAG - Start time called")
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                selectedTime.set(Calendar.YEAR, year)
                selectedTime.set(Calendar.MONTH, month)
                selectedTime.set(Calendar.DAY_OF_MONTH, month)
                showTimePicker()
            },
            selectedTime.get(Calendar.YEAR),
            selectedTime.get(Calendar.MONTH),
            selectedTime.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        TimePickerDialog(
            context, { _, hour, minute ->
                selectedTime.set(Calendar.HOUR_OF_DAY, hour)
                selectedTime.set(Calendar.MINUTE, minute)
                binding?.btnStartTime?.text = "Дата окончания: ${getTimeInstance().format(selectedTime.time)}"
            }, selectedTime.get(Calendar.HOUR_OF_DAY),
            selectedTime.get(Calendar.MINUTE), true
        ).show()

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