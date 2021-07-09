package summerpractice.team7.mymemory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.model.TaskModel
import summerpractice.team7.mymemory.databinding.TaskViewBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    val taskList = ArrayList<TaskModel>()

    var clickListener: ((taskModel: TaskModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            TaskViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(
        private val binding: TaskViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskModel) = with(binding) {
            taskName.text = item.name
            aboutTask.text = item.description
            startOfTime.text = item.startDate.toString()
            endOfTime.text = item.endDate.toString()
            clickListener?.invoke(item)
        }
    }


    fun addTask(taskModel: TaskModel) {
        taskList.add(taskModel)
        notifyItemChanged(taskList.indexOf(taskModel))
    }

    fun removeTask(taskModel: TaskModel): ArrayList<TaskModel> {
        return arrayListOf(taskList.removeAt(taskList.indexOf(taskModel)))
    }
}