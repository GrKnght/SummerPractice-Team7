package summerpractice.team7.mymemory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import summerpractice.team7.mymemory.db.dao.TasksDao.TaskStatus
import java.io.Serializable

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var description: String?,
    @ColumnInfo(name = "sd") var start_date: Long? = null,
    @ColumnInfo(name = "ed") var end_date: Long? = null,
    @ColumnInfo(name = "status") var status: TaskStatus = TaskStatus.NotStarted
) : Serializable