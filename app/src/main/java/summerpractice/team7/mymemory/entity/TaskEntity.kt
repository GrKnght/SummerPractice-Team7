package summerpractice.team7.mymemory.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import summerpractice.team7.mymemory.dao.TasksDao.TaskStatus

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "desc") val description: String?,
    @ColumnInfo(name = "sd") val start_date: Long? = null,
    @ColumnInfo(name = "ed") val end_date: Long? = null,
    @ColumnInfo(name = "status") val status: TaskStatus = TaskStatus.NotStarted
)