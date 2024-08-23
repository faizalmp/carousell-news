package id.faizalempe.carousellnews.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback<T: Any>(
    private val areContentsTheSame: (old: T, new: T) -> Boolean = { old, new -> old == new },
    private val areItemsTheSame: (old: T, new: T) -> Boolean = { old, new -> old == new },
): DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame.invoke(oldItem, newItem)

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame.invoke(oldItem, newItem)
}