package com.example.babytracker.ui.fragments.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babytracker.data.entity.SettingsItem
import com.example.babytracker.databinding.SettingsCardBinding
import com.example.babytracker.utils.Constant

class SettingsItemAdapter(private val settingsItem: List<SettingsItem>, private val mContext: Context): RecyclerView.Adapter<SettingsItemAdapter.ViewHolder>()  {

    inner class ViewHolder(var binding: SettingsCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SettingsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingsItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = settingsItem[position]
        val h = holder.binding

        h.imageViewItemIcon.setImageResource(item.icon)
        h.textViewItemName.setText(item.nameCategory)

        h.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("${Constant.SETTING_URL}")
            mContext.startActivity(intent)
        }
    }
}