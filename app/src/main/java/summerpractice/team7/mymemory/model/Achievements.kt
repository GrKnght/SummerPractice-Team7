package summerpractice.team7.mymemory.model

import android.content.Context
import android.widget.ImageView

class Achievement(val name: String, private val drawableName: String?) {
    fun setDrawable(imageView: ImageView) {
        if (drawableName !== null) {
            imageView.setImageResource(resIdByName(imageView.context, this.drawableName))
        } else {
            // If we don't have Achievement icon image yet (or deprecated this meme), put not found icon instead
            imageView.setImageResource(resIdByName(imageView.context, "no_tasks_icon"))
        }
    }
}

fun resIdByName(context: Context, drawableName: String): Int {
    return context.resources.getIdentifier(
        drawableName,
        "drawable",
        context.packageName
    )
}

public val AchievementList: List<Achievement> = arrayListOf(
    Achievement("RarePepe","all_tasks_icon"),
    // Just to test not found icon
    Achievement("Unknown", null)
)