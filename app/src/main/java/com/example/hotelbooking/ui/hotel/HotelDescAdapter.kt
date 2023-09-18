package com.example.hotelbooking.ui.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.databinding.HotelsDescriptionItemBinding

class HotelDescAdapter (private val listDescriptions: List<String>) : RecyclerView.Adapter<HotelDescViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelDescViewHolder {
        return HotelDescViewHolder(
            HotelsDescriptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun getItemCount() = listDescriptions.size

    override fun onBindViewHolder(holder: HotelDescViewHolder, position: Int) {

        val desc = listDescriptions[position]
        with(holder.bindingDesign) {
            textViewHotelDescription.text = desc
        }
    }
}
class HotelDescViewHolder(val bindingDesign: HotelsDescriptionItemBinding) : RecyclerView.ViewHolder(bindingDesign.root)