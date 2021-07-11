package summerpractice.team7.mymemory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "path") val drawableName: String?,
    // if null, then not unlocked:
    @ColumnInfo(name = "time") val unlockedAt: Long = -1L
)