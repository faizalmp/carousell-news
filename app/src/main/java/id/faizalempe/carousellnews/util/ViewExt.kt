package id.faizalempe.carousellnews.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.faizalempe.carousellnews.base.recyclerview.LinearMarginItemDecoration

fun RecyclerView?.setItemMargin(margin: Int) {
    this ?: return
    addItemDecoration(LinearMarginItemDecoration(margin))
}

fun ImageView?.loadImage(imageUrl: String?, @DrawableRes placeholderId: Int = 0) {
    this ?: return
    context ?: return
    Glide.with(context)
        .load(imageUrl.orEmpty())
        .placeholder(placeholderId)
        .into(this)
}