package com.jyun_yi.kotlin_demo.ui.adapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jyun_yi.kotlin_demo.databinding.ItemParkingEntranceBinding
import com.jyun_yi.kotlin_demo.models.EntrancecoordInfoItem

class ParkingEntranceAdapter: ListAdapter<EntrancecoordInfoItem, ParkingEntranceAdapter.MyHolder>(object: ItemCallback<EntrancecoordInfoItem>() {
    override fun areItemsTheSame(oldItem: EntrancecoordInfoItem, newItem: EntrancecoordInfoItem) = oldItem.lat==newItem.lat && oldItem.lng==newItem.lng
    override fun areContentsTheSame(oldItem: EntrancecoordInfoItem, newItem: EntrancecoordInfoItem) = oldItem == newItem
}) {
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemParkingEntranceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    class MyHolder(private val binding: ItemParkingEntranceBinding): ViewHolder(binding.root) {
        fun bind(item: EntrancecoordInfoItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}