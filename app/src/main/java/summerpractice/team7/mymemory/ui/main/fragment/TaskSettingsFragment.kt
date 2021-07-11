package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentTaskSettingsBinding

class TaskSettingsFragment : Fragment() {

    private var binding: FragmentTaskSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskSettingsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        inited()
    }

//    private fun inited() {
//        binding?.taskCompleted?.setOnClickListener {
//
//        }
//    }
}