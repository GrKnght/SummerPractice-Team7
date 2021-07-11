package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.FragmentAchievementBinding
import summerpractice.team7.mymemory.ui.adapter.AchievementAdapter
import summerpractice.team7.mymemory.ui.main.MainActivity

class AchievementFragment : Fragment() {

    var binding: FragmentAchievementBinding? = null
    var adapter = AchievementAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initing()
    }

    private fun initing() {
        binding?.achievementRectangles?.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        binding?.achievementRectangles?.adapter = adapter
        for (achievement in (requireActivity() as MainActivity).db.achievementDao().getAllUnlocked()) {
            adapter.addAchievement(achievement)
        }
        setupNoAchievementsIcons()
    }

    private fun setupNoAchievementsIcons() {
        if ((requireActivity() as MainActivity).db.achievementDao().getAllUnlocked().isNotEmpty()) {
            binding?.imageView?.visibility = View.INVISIBLE
            binding?.textView?.visibility = View.INVISIBLE
        } else {
            binding?.imageView?.visibility = View.VISIBLE
            binding?.textView?.visibility = View.VISIBLE
        }
    }


}