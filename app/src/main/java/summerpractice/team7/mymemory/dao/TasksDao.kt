package summerpractice.team7.mymemory.dao

import androidx.room.*
import summerpractice.team7.mymemory.entity.TaskEntity

@Dao
interface TasksDao {
    enum class TaskStatus {
        NotStarted,
        InProgress,
        Finished,
        Failed
    }

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE id IN (:ids)")
    fun getMultiple(ids: IntArray): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE (id=:id)")
    fun get(id: Int): TaskEntity

    @Query("SELECT * FROM tasks WHERE (status=:status)")
    fun getTasksWithStatus(status: TaskStatus): List<TaskEntity>

    @Insert
    fun addMultiple(vararg tasks: TaskEntity)

    @Insert
    fun add(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)

    @Update
    fun updateMultiple(vararg tasks: TaskEntity)

    @Query("UPDATE tasks SET status=:status WHERE (id=:id)")
    fun updateStatus(id: Int, status: TaskStatus)

    @Delete
    fun delete(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE (id=:id)")
    fun deleteById(id: Int)

    fun updateOutdatedStatuses() : List<TaskEntity> {
        // NotStarted => InProgress by start_date
        val updatedTasks: MutableList<TaskEntity> = mutableListOf()
        val unixTime = System.currentTimeMillis() / 1000L
        val inProgressTasks: List<TaskEntity> = this.getTasksWithStatus(TaskStatus.InProgress)
        for (task in inProgressTasks) {
            if (task.end_date !== null) {
                if (unixTime > task.end_date) {
                    this.updateStatus(task.id, TaskStatus.Failed)
                    updatedTasks.add(this.get(task.id))
                }
            }
        }
        val notStartedTasks: List<TaskEntity> = this.getTasksWithStatus(TaskStatus.NotStarted)
        for (task in notStartedTasks) {
            if (task.start_date !== null) {
                if (unixTime > task.start_date) {
                    this.updateStatus(task.id, TaskStatus.InProgress)
                    updatedTasks.add(this.get(task.id))
                }
            }
        }
        /*
        If updatedTasks array contains InProgress task, then
        this task was NotStarted before,
        you should add notification, that this task started.
        Same for Failed task, because time ran out, notify
        user about failed task
         */
        return updatedTasks
    }
}