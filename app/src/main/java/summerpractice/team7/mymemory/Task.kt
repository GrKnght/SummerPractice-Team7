package summerpractice.team7.mymemory

data class Task(var id: Int, var name: String, var description: String,
                var start_date: Long? = null, var end_date: Long? = null,
                var status: Int)
