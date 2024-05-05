package com.jyun_yi.kotlin_demo.ui.adapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jyun_yi.kotlin_demo.databinding.ItemParkingSearchBinding
import com.jyun_yi.kotlin_demo.models.ParkingLocation

class ParkingSearchAdapter(private val onClick: (item: ParkingLocation) -> Unit):
ListAdapter<ParkingLocation, ParkingSearchAdapter.MyHolder>(object: ItemCallback<ParkingLocation>() {
    override fun areItemsTheSame(oldItem: ParkingLocation, newItem: ParkingLocation) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: ParkingLocation, newItem: ParkingLocation) = oldItem == newItem
}) {
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemParkingSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.onClick = this.onClick
        return MyHolder(binding)
    }

    class MyHolder(private val binding: ItemParkingSearchBinding): ViewHolder(binding.root) {
        fun bind(item: ParkingLocation) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}