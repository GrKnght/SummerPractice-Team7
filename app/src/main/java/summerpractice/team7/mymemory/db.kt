package summerpractice.team7.mymemory

import androidx.room.*

enum class TaskStatus {
    NotStarted,
    InProgress,
    Finished,
    Failed
}

class db {
    @Entity(tableName = "tasks")
    data class Task(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "desc") val description: String?,
        @ColumnInfo(name = "sd") val start_date: Long? = null,
        @ColumnInfo(name = "ed") val end_date: Long? = null,
        @ColumnInfo(name = "status") val status: TaskStatus = TaskStatus.NotStarted
    )

    @Database(entities = [Task::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun tasksDao(): TasksDao
    }

    @Dao
    interface TasksDao {
        @Query("SELECT * FROM tasks")
        fun getAll(): List<Task>

        @Query("SELECT * FROM tasks WHERE id IN (:ids)")
        fun getMultiple(ids: IntArray): List<Task>

        @Query("SELECT * FROM tasks WHERE (id=:id)")
        fun get(id: Int): List<Task>

        @Query("SELECT * FROM tasks WHERE (status=:status)")
        fun getTasksWithStatus(status: TaskStatus): List<Task>

        @Insert
        fun addAll(vararg tasks: Task)

        @Delete
        fun delete(task: Task)
    }
}