package summerpractice.team7.mymemory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.TaskAdapter
import summerpractice.team7.mymemory.databinding.FragmentDaylyTaskBinding
import summerpractice.team7.mymemory.model.Task
import java.security.InvalidParameterException

class DaylyTaskFragment : Fragment() {

    private var binding: FragmentDaylyTaskBinding? = null
    private val adapter = TaskAdapter()

    private fun getTask(): Task? = arguments?.getSerializable(TASK_TAG) as? Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDaylyTaskBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        loadTasks()
        addTask()
    }

    private fun addTask() {
        getTask()?.let { task ->
            adapter.addTask(task)
        }
    }

    private fun loadTasks() {
        // TODO Загрузка из БД, передача тасков адаптеру RV

    }

    private fun init() {
        binding?.rectangles?.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding?.rectangles?.adapter = adapter
        val addingTask = binding?.addingTask
        addingTask?.setOnClickListener {
            adapter.addTask(
                Task(
                    1, "Пока ничего", "Пока тоже ничего",
                    10101010, 202020022, 12
                )
            )
        }
    }

    private fun setupNoTasksNotifications() {
        if (arrayListOf(binding?.rectangles).size == 0) {
            binding?.imageView?.visibility = INVISIBLE
            binding?.textView?.visibility = INVISIBLE
        } else {
            binding?.imageView?.visibility = VISIBLE
            binding?.textView?.visibility = VISIBLE
        }
    }

    companion object {

        private const val TASK_TAG = "TAG_NEW_TASK"

        fun getInstance(task: Task? = null): DaylyTaskFragment {
            val fragment = DaylyTaskFragment()
            task?.let { task ->
                fragment.arguments = Bundle().apply {
                    putSerializable(TASK_TAG, task)
                }
            }
            return fragment
        }
    }
}

//            binding?.imageView?.visibility = when (binding?.imageView?.visibility) {
//                View.VISIBLE -> View.INVISIBLE
//                View.INVISIBLE -> View.VISIBLE
//                else -> View.VISIBLE
//            }
//
//            binding?.textView?.visibility = when (binding?.textView?.visibility) {
//                View.VISIBLE -> View.INVISIBLE
//                View.INVISIBLE -> View.VISIBLE
//                else -> View.VISIBLE
//            }
