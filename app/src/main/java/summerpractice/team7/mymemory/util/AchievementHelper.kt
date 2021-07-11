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
    fun count(): Int {
        return achievementsDao.count()
    }
    fun defaultAchievementListCount(): Int {
        return achievementList.count()
    }
    fun initializeDB() {
        achievementsDao.addMultiple(*achievementList)
    }
    private fun generateAchievement(id: Int, name: String, drawableName: String?): AchievementEntity {
        return AchievementEntity(id, name, drawableName)
        //return AchievementEntity(id = id, name = name, drawableName = drawableName)
    }
    private val achievementList: Array<AchievementEntity> = arrayOf(
        generateAchievement(0, "one", "one"),
        generateAchievement(1, "two", "two"),
        generateAchievement(2, "three", "three"),
        generateAchievement(3, "four", "four"),
        generateAchievement(4, "five", "five"),
        generateAchievement(5, "six", "six"),
        generateAchievement(6, "seven", "seven"),
        generateAchievement(7, "eight", "eight"),
        generateAchievement(8, "nine", "nine"),
        generateAchievement(9, "ten", "ten"),
        generateAchievement(10, "eleven", "eleven")
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