package com.jyun_yi.kotlin_demo.ui.adapers

import com.jyun_yi.kotlin_demo.models.ParkingItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jyun_yi.kotlin_demo.databinding.ItemParkingInfoBinding
import com.jyun_yi.kotlin_demo.models.EntrancecoordInfoItem

class ParkingInfoAdapter: ListAdapter<ParkingItem, ParkingInfoAdapter.MyHolder>(object: ItemCallback<ParkingItem>() {
    override fun areItemsTheSame(oldItem: ParkingItem, newItem: ParkingItem) = oldItem.parkingLot.id == newItem.parkingLot.id
    override fun areContentsTheSame(oldItem: ParkingItem, newItem: ParkingItem) = oldItem == newItem
}) {
    private var keys = listOf<String>()

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemParkingInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    class MyHolder(private val binding: ItemParkingInfoBinding): ViewHolder(binding.root) {
        fun bind(item: ParkingItem) {
            binding.parkingLot = item.parkingLot
            binding.parkingSpace = item.parkingSpace
            binding.executePendingBindings()

            var entrances = item.parkingLot.entranceCoord.entrancecoordInfo
            if(entrances != null){
                entrances = entrances.map {
                    var address = it.address
                    if (address.isEmpty()){
                        address = item.parkingLot.address
                    }
                    address = item.parkingLot.area + address
                    EntrancecoordInfoItem(
                        address,
                        it.lng,
                        it.lat,
                    )
                }
                val adapter = ParkingEntranceAdapter()
                binding.parkingListEntrance.adapter = adapter
                adapter.submitList(entrances)
            }
        }
    }

    fun submitList(itemMap: Map<String, ParkingItem>) {
        keys = itemMap.keys.toList()
        val items = mutableListOf<ParkingItem>()
        keys.forEach { id ->
            items.add(itemMap[id]!!)
        }
        super.submitList(items)
    }

    fun getPositionById(id: String): Int{
        return keys.indexOf(id)
    }
}