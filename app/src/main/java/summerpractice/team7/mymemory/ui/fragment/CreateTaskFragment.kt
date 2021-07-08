package summerpractice.team7.mymemory.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentCreateTaskBinding
import summerpractice.team7.mymemory.model.Task
import summerpractice.team7.mymemory.ui.base.BaseFragment

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
        val task = Task(
            id = 1,
            name = "Задание",
            description = "Описание",
            startDate = 1L,
            endDate = 2L,
            status = 1
        )
        binding?.let {
            with(it) {
                saveTaskBtn.setOnClickListener {
                    // TODO Сохранять таск в БД
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment,
                            DaylyTaskFragment.getInstance(),
                            "DAILY_TASK_FRAGMENT"
                        )
                        .commit()
                }
            }
        }
    }
}