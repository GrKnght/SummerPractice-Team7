package summerpractice.team7.mymemory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.db.entity.TaskEntity
import summerpractice.team7.mymemory.databinding.TaskViewBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    val taskList = ArrayList<TaskEntity>()

    var completeClickListener: ((taskModel: TaskEntity) -> Unit)? = null

    var changeTimeListener: ((taskModel: TaskEntity) -> Unit)? = null

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
        fun bind(item: TaskEntity) = with(binding) {
            taskName.text = item.name
            aboutTask.text = item.description
            //taskHours.text = item.time_hours.toString()
            //taskMinutes.text = item.time_minutes.toString()
            btnEndTime.setOnClickListener {
                changeTimeListener?.invoke(item)
            }
            finishTask.setOnClickListener {
                completeClickListener?.invoke(item)
            }
        }
    }


    fun addTask(taskModel: TaskEntity) {
        taskList.add(taskModel)
        notifyItemChanged(taskList.indexOf(taskModel))
    }

    fun removeTask(taskModel: TaskEntity) {
        //return arrayListOf(taskList.removeAt(taskList.indexOf(taskModel)))
        val index = taskList.indexOf(taskModel)
        taskList.remove(taskModel)
        notifyItemChanged(index)
    }
}