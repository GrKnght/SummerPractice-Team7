package summerpractice.team7.mymemory.ui.main.fragment

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
                        id = arrayListOf((requireActivity() as MainActivity).db.tasksDao()).size,
                        name = binding?.taskNameEt?.text.toString(),
                        description = binding?.taskDescriptionEt?.text.toString(),
                        start_date = 1010,
                        end_date = 2020,
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
    }
}