package com.example.babytracker.ui.fragments.symptoms.choose_symptoms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babytracker.R
import com.example.babytracker.data.entity.symptoms.Choose_Symptoms
import com.example.babytracker.databinding.SymptomsCardBinding

interface SymptomsClickedListener{
    fun onSymptomsClicked(symptoms: String)
}

class ChooseSymptomsAdapter (
    private val sypmptomsList : List<Choose_Symptoms>,
    private val symptomsClickedListener: SymptomsClickedListener) : RecyclerView.Adapter<ChooseSymptomsAdapter.ViewHolder>(){
    class ViewHolder(val binding: SymptomsCardBinding) : RecyclerView.ViewHolder(binding.root){
        var isChoose: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SymptomsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sypmptomsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val symptoms = sypmptomsList[position]
        val item = holder.binding
        item.imageView12.setImageResource(symptoms.image)
        item.textViewSymptoms.text = symptoms.symptomsName
        holder.binding.root.setOnClickListener {
            val isChoose = holder.isChoose
            if(isChoose){
                holder.binding.cardConstraint.setBackgroundResource(R.drawable.unselected_cardview)
            }else{
                holder.binding.cardConstraint.setBackgroundResource(R.drawable.selected_card_view)
                symptomsClickedListener.onSymptomsClicked(item.textViewSymptoms.text.toString())
            }
            holder.isChoose = !isChoose
        }
    }
}