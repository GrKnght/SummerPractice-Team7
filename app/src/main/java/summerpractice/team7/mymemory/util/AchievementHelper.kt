package summerpractice.team7.mymemory.util

import android.content.Context
import android.widget.ImageView
import summerpractice.team7.mymemory.db.dao.AchievementsDao
import summerpractice.team7.mymemory.db.entity.AchievementEntity

class AchievementHelper(val achievementsDao: AchievementsDao) {
    fun unlockRandom(): AchievementEntity {
        return achievementsDao.unlockRandom()
    }
    fun lockRandom() {
        return achievementsDao.lockRandom()
    }
    fun getUnlocked(): List<AchievementEntity> {
        return achievementsDao.getAllUnlocked()
    }
    fun initializeDB() {
        achievementsDao.addMultiple(achievementList)
    }
    private fun generateAchievement(id: Int, name: String, drawableName: String?): AchievementEntity {
        return AchievementEntity(id, name, drawableName)
        //return AchievementEntity(id = id, name = name, drawableName = drawableName)
    }
    private val achievementList: ArrayList<AchievementEntity> = arrayListOf(
        generateAchievement(0,"RarePepe","all_tasks_icon"),
        // Just to test not found icon
        generateAchievement(999,"Unknown", null)
    )
    fun setDrawable(achievementEntity: AchievementEntity, imageView: ImageView) {
        if (achievementEntity.drawableName !== null) {
            imageView.setImageResource(resIdByName(imageView.context, achievementEntity.drawableName))
        } else {
            // If we don't have Achievement icon image yet (or deprecated this meme), put not found icon instead
            imageView.setImageResource(resIdByName(imageView.context, "no_tasks_icon"))
        }
    }
    private fun resIdByName(context: Context, drawableName: String): Int {
        return context.resources.getIdentifier(
            drawableName,
            "drawable",
            context.packageName
        )
    }
}