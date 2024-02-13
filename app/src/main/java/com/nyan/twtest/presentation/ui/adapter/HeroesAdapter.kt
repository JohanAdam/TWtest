package com.nyan.twtest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nyan.twtest.databinding.ListItemHeroBinding
import com.nyan.twtest.domain.entity.HeroEntity


class HeroesAdapter(private var list: List<HeroEntity>,
                    private var itemClickCallback:(HeroEntity) -> Unit) :
    RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact = list[position]

        viewHolder.bind(contact)
        viewHolder.itemView.setOnClickListener {
            itemClickCallback.invoke(contact)
        }
    }

    override fun getItemCount() = list.size

    fun updateData(newLists: List<HeroEntity>) {
        val diffResult = DiffUtil.calculateDiff(ListDiffCallback(list, newLists))
        list = newLists
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(private val binding: ListItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeroEntity) {
            binding.tvName.text = item.name
            binding.tvRating.text = when (item.rating) {
                1f -> { "Normal" }
                2f -> { "Very Good" }
                3f -> { "Awesome" }
                else -> { "No rating" }
            }
            item.photo?.let {
                binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(binding.root.context, it))
            }
        }
    }

    class ListDiffCallback(
        private val oldList: List<HeroEntity>,
        private val newList: List<HeroEntity>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
