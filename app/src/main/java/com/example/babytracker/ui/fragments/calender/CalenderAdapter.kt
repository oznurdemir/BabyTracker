package com.example.babytracker.ui.fragments.calender

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babytracker.data.entity.calender.CalenderItem
import com.example.babytracker.databinding.CalenderItemBinding

class CalenderAdapter(private var calenderItem: List<CalenderItem>): RecyclerView.Adapter<CalenderAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: CalenderItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CalenderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return calenderItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = calenderItem[position]
        val h = holder.binding

        h.textViewItemName.text = item.category
        h.textView.text = item.time
    }

    fun updateData(newList: List<CalenderItem>) {
        calenderItem = newList
        notifyDataSetChanged()
    }
}