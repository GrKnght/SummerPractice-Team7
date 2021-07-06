package summerpractice.team7.mymemory

import android.widget.ImageView

class Achievement(val name: String, private val path: String?) {
    fun setDrawable(imageView: ImageView) {
        //val imgResId = resIdByName(this.path, "drawable")
        if (path !== null) {
            imageView.setImageResource(resIdByName(path))
        } else {
            // If we don't have meme image yet (or deprecated this meme), put not found icon instead
            imageView.setImageResource(resIdByName("@drawable/no_tasks_icon.png"))
        }
    }
}

public val AchievementList: List<Achievement> = arrayListOf(
    Achievement("RarePepe","@drawable/memes/RarePepe.png")
)

fun resIdByName(path: String): Int {
    /*resIdName?.let {
        return resources.getIdentifier(it, resType, packageName)
    }
    throw Resources.NotFoundException()*/
    return 0 // Not implemented
}