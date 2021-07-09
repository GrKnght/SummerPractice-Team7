package summerpractice.team7.mymemory.model

import java.io.Serializable

data class TaskModel(
    var id: Int,
    var name: String,
    var description: String,
    var start_date: Long? = null,
    var end_date: Long? = null,
    var status: Int
): Serializable
