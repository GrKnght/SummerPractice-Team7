package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.FragmentAllTaskBinding
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.ui.adapter.AllTaskAdapter
import summerpractice.team7.mymemory.ui.main.MainActivity


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
        binding?.allTaskRectangles?.adapter = adapter
        loadTasks()
    }


    private fun loadTasks() {
        for (task in (requireActivity() as MainActivity).db.tasksDao().getAll()) {
            if (task.status == TasksDao.TaskStatus.Failed || task.status == TasksDao.TaskStatus.Finished)
                adapter.addTask(task)
        }
    }

}