package summerpractice.team7.mymemory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.AchievementViewBinding
import summerpractice.team7.mymemory.databinding.TaskViewBinding
import summerpractice.team7.mymemory.db.entity.AchievementEntity
import summerpractice.team7.mymemory.db.entity.TaskEntity

class AchievementAdapter : RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {

    val achievementList = ArrayList<AchievementEntity>()
    var binding: AchievementViewBinding? = null

    class AchievementViewHolder(
        private val binding: AchievementViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AchievementEntity) = with(binding) {

        }
    }

    fun addAchievement(achievementEntity: AchievementEntity) {
        achievementList.add(achievementEntity)
        notifyItemChanged(achievementList.indexOf(achievementEntity))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        return AchievementViewHolder(
            AchievementViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(achievementList[position])
    }

    override fun getItemCount(): Int = achievementList.size
}