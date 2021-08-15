import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

internal class ProminentLayoutManager(
    context: Context,
    private val minScaleDistanceFactor: Float = 1.5f,
    private val scaleDownBy: Float = 0.5f
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun onLayoutCompleted(state: RecyclerView.State?) =
        super.onLayoutCompleted(state).also { scaleChildren() }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ) = super.scrollHorizontallyBy(dx, recycler, state).also {
        if (orientation == HORIZONTAL) scaleChildren()
    }

    private fun scaleChildren() {
        val containerCenter = width / 2f

        // Any view further than this threshold will be fully scaled down
        val scaleDistanceThreshold = minScaleDistanceFactor * containerCenter

        for (i in 0 until childCount) {
            val child = getChildAt(i)!!

            val childCenter = (child.left + child.right) / 2f
            val distanceToCenter = abs(childCenter - containerCenter)

            val scaleDownAmount = (distanceToCenter / scaleDistanceThreshold).coerceAtMost(1f)
            val scale = 1f - scaleDownBy * scaleDownAmount

            child.scaleX = scale
            child.scaleY = scale
        }
    }
}