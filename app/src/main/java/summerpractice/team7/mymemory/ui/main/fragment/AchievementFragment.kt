package summerpractice.team7.mymemory.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.FragmentAchievementBinding
import summerpractice.team7.mymemory.ui.adapter.AchievementAdapter
import summerpractice.team7.mymemory.ui.main.MainActivity
import java.text.DateFormat
import java.util.*

class AchievementFragment : Fragment() {

    var binding: FragmentAchievementBinding? = null
    var adapter: AchievementAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AchievementAdapter(requireContext())
        initRecyclerView()
        initClickListener()
    }

    private fun initRecyclerView() {
        binding?.achievementRectangles?.layoutManager =
            LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
        binding?.achievementRectangles?.adapter = adapter
        /*for (achievement in (requireActivity() as MainActivity).db.achievementDao().getAllUnlocked()) {
            adapter?.addAchievement(achievement)
        }*/
        val list = (requireActivity() as MainActivity).db.achievementDao().getAllUnlocked()
        adapter?.achievementList = list.toMutableList()
        setupNoAchievementsIcons()
    }

    private fun initClickListener() {
        adapter?.clickListener = {
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = it.unlockedAt * 1000
            Toast.makeText(requireContext(), "«${it.name}»\n (разблокировано в ${
                DateFormat.getTimeInstance().format(calendar.time)})", Toast.LENGTH_SHORT)
                .show()
        }
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