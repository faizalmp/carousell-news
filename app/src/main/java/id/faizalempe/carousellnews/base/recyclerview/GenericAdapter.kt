package id.faizalempe.carousellnews.base.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GenericAdapter<T: Any, VB: ViewBinding>(
    areContentsTheSame: (old: T, new: T) -> Boolean = { old, new -> old == new },
    areItemsTheSame: (old: T, new: T) -> Boolean = { old, new -> old == new },
    private val itemBinding: ViewGroup.() -> VB,
    private val onBindItem: VB.(itemData: T, position: Int) -> Unit = { _, _ -> },
    private val onItemCLick: VB.(itemData: T, position: Int) -> Unit = { _, _ -> },
    private val onChanged: (list: List<T>) -> Unit = { _ -> }
): ListAdapter<T, GenericAdapter.GenericViewHolder<T, VB>>(
    DiffItemCallback(areContentsTheSame, areItemsTheSame)
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder<T, VB> = GenericViewHolder(itemBinding.invoke(parent))

    override fun onBindViewHolder(holder: GenericViewHolder<T, VB>, position: Int) {
        holder.apply { bind(getItem(adapterPosition), onBindItem, onItemCLick) }
    }

    override fun onCurrentListChanged(previousList: MutableList<T>, currentList: MutableList<T>) {
        onChanged.invoke(currentList)
    }

    fun set(newData: List<T>, afterSubmit: () -> Unit = {}) = submitList(newData, afterSubmit)

    fun clear() = submitList(null)

    class GenericViewHolder<T, VB: ViewBinding>(
        private val itemBinding: VB
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            data: T,
            onBindItem: VB.(itemData: T, position: Int) -> Unit = { _, _ -> },
            onItemCLick: VB.(itemData: T, position: Int) -> Unit = { _, _ -> }
        ) {
            onBindItem.invoke(itemBinding, data, adapterPosition)
            itemView.setOnClickListener { onItemCLick.invoke(itemBinding, data, adapterPosition) }
        }
    }
}