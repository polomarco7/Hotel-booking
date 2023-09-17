package com.example.hotelbooking

 import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelbooking.databinding.RoomItemBinding
 import com.google.android.material.tabs.TabLayoutMediator

class RoomsListAdapter(
    private val onClick: (Rooms) -> Unit
) : ListAdapter<Rooms, RoomsViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(
            RoomItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            textViewHotelDescription.text = item?.name ?: ""
            textViewPrice.text = item?.price.toString()
            textViewForDays.text = item?.pricePer
            photoSlider.viewPager2.adapter = AppIntroViewPager2Adapter(item.imageUrls)
            TabLayoutMediator(
                photoSlider.tabLayout,
                photoSlider.viewPager2
            ) { _, _ ->}.attach()
//            countries.text = item?.countries?.joinToString(", ") { it.country }
                    recyclerView.adapter = HotelDescAdapter(item.peculiarities)
        }

        holder.binding.selectRoomBtn.setOnClickListener {
            item?.let{
                onClick(item)
            }
        }
    }
}

class RoomsViewHolder(val binding: RoomItemBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Rooms>() {
    override fun areItemsTheSame(oldItem: Rooms, newItem: Rooms): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Rooms, newItem: Rooms): Boolean = oldItem == newItem
}
