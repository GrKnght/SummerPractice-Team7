package summerpractice.team7.mymemory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.FragmentCreateTaskBinding

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
        daylyTaskFragment: DaylyTaskFragment
    ) {
        rectangles?.adapter = adapter
        val createTask = binding?.completing
        createTask?.setOnClickListener {
            adapter.addTask(Task(1, binding?.taskName.toString(),
            binding?.taskDescription.toString(), 10101, 12123, 1))
            parentFragmentManager.beginTransaction()
                .replace(R.id.createTaskFragment, daylyTaskFragment)
                .commit()
        }
    }

}