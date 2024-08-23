package id.faizalempe.carousellnews.base.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearMarginItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position < parent.childCount) {
            when ((parent.layoutManager as? LinearLayoutManager)?.orientation) {
                RecyclerView.HORIZONTAL -> outRect.right += spacing
                RecyclerView.VERTICAL -> outRect.bottom += spacing
            }
        }
    }
}