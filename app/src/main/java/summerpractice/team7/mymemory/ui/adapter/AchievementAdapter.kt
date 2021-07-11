package summerpractice.team7.mymemory.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.R
import summerpractice.team7.mymemory.databinding.ItemAchievementBinding
import summerpractice.team7.mymemory.db.entity.AchievementEntity

class AchievementAdapter(private val context: Context) :
    RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {

    var achievementList = mutableListOf<AchievementEntity>()

    var clickListener: ((AchievementEntity) -> Unit)? = null

    inner class AchievementViewHolder(
        private val binding: ItemAchievementBinding,
        val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AchievementEntity) = with(binding) {
            /*achievementIv.setImageDrawable(
                ContextCompat.getDrawable(context, R.drawable.all_tasks_icon)
            )*/
            achievementIv.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    context.resources.getIdentifier(
                        item.drawableName,
                        "drawable",
                        context.packageName
                    )
                )
            )
            achievementContainer.setOnClickListener {
                clickListener?.invoke(item)
            }
        }
    }

    fun addAchievement(achievementEntity: AchievementEntity) {
        achievementList.add(achievementEntity)
        notifyItemChanged(achievementList.indexOf(achievementEntity))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        return AchievementViewHolder(
            ItemAchievementBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(achievementList[position])
    }

    override fun getItemCount(): Int = achievementList.size
}