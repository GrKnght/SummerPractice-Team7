package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentAllTaskBinding
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.ui.adapter.AllTaskAdapter
import summerpractice.team7.mymemory.ui.main.MainActivity
import java.util.function.Predicate
import java.util.stream.Collector

class AllTaskFragment : Fragment() {

    private var binding: FragmentAllTaskBinding? = null
    private val adapter = AllTaskAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTaskBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initing()
    }

    private fun initing() {
        binding?.allTaskRectangles?.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        (requireActivity() as MainActivity).db.tasksDao().add(
                TaskEntity(id = (requireActivity() as MainActivity).db.tasksDao().getAll().size,
                    name ="Проверка",
                    description = "Списка всех задач",
                    start_date = 1L,
                    end_date = 2L,
                    status = TasksDao.TaskStatus.Finished
                ))
        loadTasks()
    }


    private fun loadTasks() {
        val addes = ArrayList<TaskEntity>()
        for (task in (requireActivity() as MainActivity).db.tasksDao().getAll()) {
            if (task.status == TasksDao.TaskStatus.Failed || task.status == TasksDao.TaskStatus.Finished)
                adapter.addTask(task)
        }

    }
}