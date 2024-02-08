package com.example.babytracker.ui.fragments.calender

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babytracker.R
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

        h.textView.text = item.time

        if(item.note.isEmpty()){
            h.textViewItemNote.text = "No note."
        }else{
            h.textViewItemNote.text = item.note
        }

        when(item.category){
            "symptoms" ->{
                val symptomText = SpannableStringBuilder("Symptom: ${item.symptoms}")
                symptomText.setSpan(ForegroundColorSpan(Color.parseColor("#4625C3")), 0, 8, 0)
                symptomText.setSpan(ForegroundColorSpan(Color.parseColor("#010101")), 9, symptomText.length, 0)

                h.imageViewItemIcon.setImageResource(R.drawable.symptoms_icon_color)
                h.textViewItemName.text = symptomText
            }
            "feeding" -> {
                val amountText = SpannableStringBuilder("Amount: ${item.amount}")
                amountText.setSpan(ForegroundColorSpan(Color.parseColor("#4625C3")), 0, 7, 0)
                amountText.setSpan(ForegroundColorSpan(Color.parseColor("#010101")), 8, amountText.length, 0)

                h.imageViewItemIcon.setImageResource(R.drawable.feeidng_icon_color)
                h.textViewItemName.text = amountText
            }
            "sleep" -> {
                val feelTimeText = SpannableStringBuilder("Feel Time: ${item.fellTime}")
                feelTimeText.setSpan(ForegroundColorSpan(Color.parseColor("#4625C3")), 0, 10, 0)
                feelTimeText.setSpan(ForegroundColorSpan(Color.parseColor("#010101")), 11, feelTimeText.length, 0)

                h.imageViewItemIcon.setImageResource(R.drawable.sleep_icon_color)
                h.textViewItemName.text = feelTimeText
            }
        }
    }

    fun updateData(newList: List<CalenderItem>) {
        calenderItem = newList
        notifyDataSetChanged()
    }
}