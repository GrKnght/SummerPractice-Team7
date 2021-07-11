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
        generateAchievement(0, "memes_one", "memes_one"),
        generateAchievement(1, "memes_two", "memes_two"),
        generateAchievement(2, "memes_three", "memes_three"),
        generateAchievement(3, "memes_four", "memes_four"),
        generateAchievement(4, "memes_five", "memes_five"),
        generateAchievement(5, "memes_six", "memes_six"),
        generateAchievement(6, "memes_seven", "memes_seven"),
        generateAchievement(7, "memes_eight", "memes_eight"),
        generateAchievement(8, "memes_nine", "memes_nine"),
        generateAchievement(9, "memes_ten", "memes_ten"),
        generateAchievement(10, "memes_eleven", "memes_eleven")
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