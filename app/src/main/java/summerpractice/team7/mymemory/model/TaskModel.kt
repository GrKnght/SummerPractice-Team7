package summerpractice.team7.mymemory.model

import java.io.Serializable

data class TaskModel(
    var id: Int,
    var name: String,
    var description: String,
    var time_hours: Long? = null,
    var time_minutes: Long? = null,
    var status: Int
): Serializable
