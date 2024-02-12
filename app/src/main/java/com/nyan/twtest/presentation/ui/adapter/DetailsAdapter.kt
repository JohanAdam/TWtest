package com.nyan.twtest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nyan.twtest.databinding.ListItemTestBinding


class DetailsAdapter(private var lists: List<String>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemTestBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(lists[position])
    }

    override fun getItemCount() = lists.size

    fun updateData(newLists: List<String>) {
        val diffResult = DiffUtil.calculateDiff(ListDiffCallback(lists, newLists))
        lists = newLists
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(private val binding: ListItemTestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvText.text = item
        }
    }

    class ListDiffCallback(
        private val oldList: List<String>,
        private val newList: List<String>
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
