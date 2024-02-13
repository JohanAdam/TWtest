package com.nyan.twtest.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nyan.twtest.R
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

        val context: Context = binding.root.context

        fun bind(item: HeroEntity) {
            binding.tvName.text = item.name
            binding.tvRating.text = when (item.rating) {
                1f -> { ContextCompat.getString(context, R.string.rating_1) }
                2f -> { ContextCompat.getString(context, R.string.rating_2) }
                3f -> { ContextCompat.getString(context, R.string.rating_3) }
                else -> { ContextCompat.getString(context, R.string.no_rating) }
            }
            item.photo?.let {
                binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(context, it))
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
