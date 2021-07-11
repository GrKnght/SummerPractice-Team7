package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.databinding.FragmentDailyTaskBinding
import summerpractice.team7.mymemory.ui.adapter.TaskAdapter
import summerpractice.team7.mymemory.ui.main.MainActivity

class DailyTaskFragment : Fragment() {

    private var binding: FragmentDailyTaskBinding? = null
    private val adapter = TaskAdapter()


    private fun getTask(): TaskEntity? = arguments?.getSerializable(TASK_TAG) as? TaskEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyTaskBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addTask()
    }

    fun addTask() {
        getTask()?.let { task ->
            adapter.addTask(task)
            setupNoTasksNotifications()
        }
    }

    private fun loadTasks() {
        var addes: ArrayList<TaskEntity> = (requireActivity() as MainActivity).db.tasksDao().getAll() as ArrayList<TaskEntity>
        for (task in addes) {
            adapter.addTask(task)
        }
    }

    private fun init() {
        binding?.rectangles?.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding?.rectangles?.adapter = adapter
        loadTasks()
        val addingTask = binding?.addingTask
        addingTask?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment, CreateTaskFragment())
                .commit()
        }
    }

    fun setupNoTasksNotifications() {
        if (arrayListOf(binding?.rectangles).size != 0) {
            binding?.imageView?.visibility = INVISIBLE
            binding?.textView?.visibility = INVISIBLE
        } else {
            binding?.imageView?.visibility = VISIBLE
            binding?.textView?.visibility = VISIBLE
        }
    }

    companion object {

        private const val TASK_TAG = "TAG_NEW_TASK"

        fun getInstance(task: TaskEntity? = null): DailyTaskFragment {
            val fragment = DailyTaskFragment()
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
