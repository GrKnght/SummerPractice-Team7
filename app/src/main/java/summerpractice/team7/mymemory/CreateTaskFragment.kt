package summerpractice.team7.mymemory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.FragmentCreateTaskBinding
import summerpractice.team7.mymemory.model.TaskModel
import summerpractice.team7.mymemory.ui.adapter.TaskAdapter
import summerpractice.team7.mymemory.ui.main.fragment.DailyTaskFragment

class CreateTaskFragment : Fragment() {

    private var binding: FragmentCreateTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateTaskBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initialize(
        rectangles: RecyclerView?,
        adapter: TaskAdapter,
        daylyTaskFragment: DailyTaskFragment
    ) {
        rectangles?.adapter = adapter
        val createTask = binding?.saveTaskBtn
        createTask?.setOnClickListener {
            adapter.addTask(
                TaskModel(1, binding?.taskNameEt.toString(),
            binding?.taskDescriptionEt.toString(), 10101, 12123, 1)
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.creating_frag, daylyTaskFragment)
                .commit()
        }
    }

}