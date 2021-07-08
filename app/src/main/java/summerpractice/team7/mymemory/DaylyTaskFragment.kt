package summerpractice.team7.mymemory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.FragmentDaylyTaskBinding

class DaylyTaskFragment : Fragment() {

    private var binding: FragmentDaylyTaskBinding? = null
    private val adapter = TaskAdapter()

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
    }

    fun init() {
        binding?.rectangles?.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding?.rectangles?.adapter = adapter
        val daylyTaskFragment: DaylyTaskFragment = this
        val addingTask = binding?.addingTask
        val creatingTask = CreateTaskFragment()
        addingTask?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .commit()
            creatingTask.initialize(binding?.rectangles, adapter, daylyTaskFragment)
            setupNoTasksNotifications()
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
