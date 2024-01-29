package com.example.babytracker.ui.fragments.feeding

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentFeedingBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedingFragment : Fragment() {
    private lateinit var binding: FragmentFeedingBinding
    private lateinit var viewModel: FeedingViewModel
    private var time = ""
    private var amount = ""
    private var note = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedingBinding.inflate(inflater, container, false)
        binding.imageViewFeedingBack.setOnClickListener {
            findNavController().navigate(R.id.action_feedingFragment_to_homeFragment)
        }
        binding.imageViewChooseTime.setOnClickListener {
            openTimePicker { selectedTime ->
                binding.editTextTime.setText(selectedTime)
                time = selectedTime
            }
        }
        binding.buttonSaveFeeding.setOnClickListener {
            amount = "${binding.editTextAmount.text} ml"
            note = binding.editTextNote.text.toString()
            if (time.isNotEmpty() && amount.length > 3){
                save(time, amount, note)
                Snackbar.make(it, R.string.snackbar2, Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(it,R.string.snackbar1, Snackbar.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun save(time: String, amount: String, note: String) {
        viewModel.saveFeeding(time, amount, note)
    }

    private fun openTimePicker(callback: (String) -> Unit) {
        val isSystem24Hour = is24HourFormat(requireContext())
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText(R.string.choose_time)
            .build()
        picker.show(childFragmentManager, "TAG")

        picker.addOnPositiveButtonClickListener {
            val selectedHour = picker.hour
            val selectedMinute = picker.minute
            val isPm = if (clockFormat == TimeFormat.CLOCK_12H) {
                selectedHour in 12..23
            } else {
                false
            }
            val timeString = formatTime(selectedHour, selectedMinute, isPm)
            // Callback fonksiyonunu çağırarak seçilen zamanı iletebiliriz(Asenkdron işlem yapıldığı için kullanıyoruz.)
            callback(timeString)
        }
    }

    private fun formatTime(hour: Int, minute: Int, isAm: Boolean): String {
        val formattedHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
        val amPm = if (isAm) "PM" else "AM"
        return String.format("%02d:%02d %s", formattedHour, minute, amPm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FeedingViewModel by viewModels()
        viewModel = tempViewModel
    }
}