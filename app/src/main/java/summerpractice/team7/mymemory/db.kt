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

    fun createDBInstance(activity: Activity): AppDatabase {
        return Room.databaseBuilder(
            activity,
            AppDatabase::class.java, "mymemory"
        ).build()
    }

    @Database(entities = [Task::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun tasksDao(): TasksDao
    }

    // Примеры:
    // val db = createDBInstance(MainActivity)
    // val taskDao = db.tasksDao()
    // val inProgressTasks: List<Task> = taskDao.getTasksWithStatus(TaskStatus.InProgress)
    // val tasks: List<Task> = taskDao.getAll()

    @Dao
    interface TasksDao {
        @Query("SELECT * FROM tasks")
        fun getAll(): List<Task>

        @Query("SELECT * FROM tasks WHERE id IN (:ids)")
        fun getMultiple(ids: IntArray): List<Task>

        @Query("SELECT * FROM tasks WHERE (id=:id)")
        fun get(id: Int): Task

        @Query("SELECT * FROM tasks WHERE (status=:status)")
        fun getTasksWithStatus(status: TaskStatus): List<Task>

        @Insert
        fun addMultiple(vararg tasks: Task)

        @Insert
        fun add(task: Task)

        @Update
        fun update(task: Task?)

        @Update
        fun updateMultiple(vararg tasks: Task)

        @Query("UPDATE tasks SET status=:status WHERE (id=:id)")
        fun updateStatus(id: Int, status: TaskStatus)

        @Delete
        fun delete(task: Task)

        @Query("DELETE FROM tasks WHERE (id=:id)")
        fun deleteById(id: Int)
    }
}