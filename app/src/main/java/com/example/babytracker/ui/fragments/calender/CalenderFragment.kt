package com.example.babytracker.ui.fragments.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentCalenderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalenderFragment : Fragment() {
    private lateinit var binding: FragmentCalenderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        // Click olaylarını ayarla
        setupClickListeners()
        return binding.root
    }

    // ImageView'ların click olaylarını ayarlamak için kullanılan metot
    private fun setupClickListeners() {
        // ImageView'ları ve ilişkili drawable kaynaklarını bir liste içinde çift olarak tut
        val imageViews = listOf(
            binding.imageViewAll to R.drawable.all_icon,
            binding.imageViewFeeding to R.drawable.feeding_icon,
            binding.imageViewSleep to R.drawable.sleeping_icon,
            binding.imageViewSymptoms to R.drawable.symptoms_icon
        )
        // Her bir ImageView için click listener ayarla
        imageViews.forEach { (imageView, resourceId) ->
            imageView.setOnClickListener {
                resetAllImageResources()
                getImageViewByResource(resourceId)?.setImageResource(getSelectedResource(resourceId))
            }
        }

        binding.imageViewCalendarBack.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_homeFragment)
        }
    }

    // Tüm ImageView'ların kaynaklarını sıfırlayan metot
    private fun resetAllImageResources() {
        val imageResources = listOf(
            R.drawable.all_icon,
            R.drawable.feeding_icon,
            R.drawable.sleeping_icon,
            R.drawable.symptoms_icon
        )

        imageResources.forEach { resourceId ->
            getImageViewByResource(resourceId)?.setImageResource(resourceId)
        }
    }

    // Seçilen drawable kaynağını belirleyen metot
    private fun getSelectedResource(defaultResource: Int): Int {
        return when (defaultResource) {
            R.drawable.all_icon -> R.drawable.all_icon_color
            R.drawable.feeding_icon -> R.drawable.feeidng_icon_color
            R.drawable.sleeping_icon -> R.drawable.sleep_icon_color
            R.drawable.symptoms_icon -> R.drawable.symptoms_icon_color
            else -> defaultResource
        }
    }
    // Drawable kaynağına göre ilgili ImageView'ı döndüren metot
    private fun getImageViewByResource(resourceId: Int): ImageView? {
        return when (resourceId) {
            R.drawable.all_icon -> binding.imageViewAll
            R.drawable.feeding_icon -> binding.imageViewFeeding
            R.drawable.sleeping_icon -> binding.imageViewSleep
            R.drawable.symptoms_icon -> binding.imageViewSymptoms
            else -> null
        }
    }
}
