package summerpractice.team7.mymemory.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.TaskViewBinding
import summerpractice.team7.mymemory.db.dao.TasksDao
import summerpractice.team7.mymemory.db.entity.TaskEntity

class AllTaskAdapter : RecyclerView.Adapter<AllTaskAdapter.AllTaskViewHolder>() {

    val taskList = ArrayList<TaskEntity>()
    var binding: TaskViewBinding? = null


    class AllTaskViewHolder(
        private val binding: TaskViewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskEntity) = with(binding) {
            taskName.text = item.name
            aboutTask.text = item.description
            taskHours.text = "${taskHours.text}     ${item.time_hours}"
            taskMinutes.text = "${taskMinutes.text}     ${item.time_minutes}"
        }
    }

    fun addTask(task: TaskEntity) {
        when (task.status) {
            TasksDao.TaskStatus.Finished -> binding?.taskCard?.setCardBackgroundColor(Color.GREEN)
            TasksDao.TaskStatus.Failed -> binding?.taskCard?.setCardBackgroundColor(Color.RED)
        }
        taskList.add(task)
        notifyItemChanged(taskList.indexOf(task))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTaskViewHolder {
        binding = TaskViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllTaskViewHolder(
            TaskViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllTaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}