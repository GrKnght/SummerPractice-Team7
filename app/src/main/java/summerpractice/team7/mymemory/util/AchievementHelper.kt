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
        generateAchievement(0, "Лапки в тапки", "memes_one"),
        generateAchievement(1, "Ничего не понятно...", "memes_two"),
        generateAchievement(2, "Эвоки", "memes_three"),
        generateAchievement(3, "Собака и пузырек", "memes_four"),
        generateAchievement(4, "Котик и пузырек", "memes_five"),
        generateAchievement(5, "Возмущенная жаба", "memes_six"),
        generateAchievement(6, "Секта лягушек", "memes_seven"),
        generateAchievement(7, "Кошка-картошка", "memes_eight"),
        generateAchievement(8, "Лень тюленя", "memes_nine"),
        generateAchievement(10, "В раздумье...", "memes_eleven")
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