package summerpractice.team7.mymemory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import summerpractice.team7.mymemory.databinding.TaskViewBinding
import summerpractice.team7.mymemory.model.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    val taskList = ArrayList<Task>()

    class TaskHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = TaskViewBinding.bind(item)

        fun bind(task: Task) = with(binding) {
            taskName.text = task.name
            aboutTask.text = task.description
            startOfTime.text = task.startDate.toString()
            endOfTime.text = task.endDate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_view, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task) {
        taskList.add(task)
        notifyItemChanged(taskList.indexOf(task))
    }

    fun removeTask(task: Task): ArrayList<Task> {
        return arrayListOf(taskList.removeAt(taskList.indexOf(task)))
    }
}