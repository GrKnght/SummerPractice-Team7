package summerpractice.team7.mymemory.db.dao

import android.util.Log
import androidx.room.*
import summerpractice.team7.mymemory.db.entity.AchievementEntity

@Dao
interface AchievementsDao {
    @Query("SELECT * FROM achievements")
    fun getAll(): List<AchievementEntity>

    @Query("SELECT * FROM achievements WHERE id IN (:ids)")
    fun getMultiple(ids: IntArray): List<AchievementEntity>

    @Query("SELECT * FROM achievements WHERE (id=:id)")
    fun get(id: Int): AchievementEntity

    @Query("SELECT * FROM achievements WHERE (time = null)")
    fun getAllLocked(): List<AchievementEntity>

    @Query("SELECT * FROM achievements WHERE (time != null)")
    fun getAllUnlocked(): List<AchievementEntity>

    @Insert
    fun addMultiple(vararg achievements: AchievementEntity)

    @Insert
    fun add(task: AchievementEntity)

    @Update
    fun update(task: AchievementEntity)

    @Query("SELECT COUNT(1) FROM achievements")
    fun count(): Int

    @Update
    fun updateMultiple(vararg achievements: AchievementEntity)

    @Query("SELECT * FROM achievements WHERE (time = null) ORDER BY RANDOM() LIMIT 1")
    fun getRandomUnlocked(): AchievementEntity

    @Query("SELECT * FROM achievements WHERE (time = null) ORDER BY RANDOM() LIMIT 1")
    fun getRandomLocked(): AchievementEntity

    @Query("DELETE FROM achievements")
    fun destroyTable()

    @Transaction
    fun unlockRandom(): AchievementEntity {
        return try {
            val achievement: AchievementEntity = getRandomLocked()
            unlockById(achievement.id)
            get(achievement.id)
        } catch (e: Exception) {
            // User unlocked all achievements, just return random already unlocked one
            Log.d("AchievementsDao", "User already has all achievements, returning random instead")
            getRandomUnlocked()
        }
    }

    @Transaction
    fun lockRandom() {
        try {
            lockById(getRandomUnlocked().id)
        } catch (e: Exception) {}
    }

    @Query("UPDATE achievements SET time=null WHERE (id=:id)")
    fun lockById(id: Int)

    @Query("UPDATE achievements SET time=:unlockedAt WHERE (id=:id)")
    fun unlockById(id: Int, unlockedAt: Long? = (System.currentTimeMillis() / 1000L))

    @Delete
    fun delete(task: AchievementEntity)

    @Query("DELETE FROM achievements WHERE (id=:id)")
    fun deleteById(id: Int)
}