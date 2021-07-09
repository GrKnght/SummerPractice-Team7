package summerpractice.team7.mymemory.model

import java.io.Serializable

data class TaskModel(
    var id: Int,
    var name: String,
    var description: String,
    var startDate: Long? = null,
    var endDate: Long? = null,
    var status: Int
): Serializable
