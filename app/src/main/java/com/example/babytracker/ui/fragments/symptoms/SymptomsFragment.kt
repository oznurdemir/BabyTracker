package com.example.babytracker.ui.fragments.symptoms

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentSymptomsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SymptomsFragment : Fragment() {
    private lateinit var binding: FragmentSymptomsBinding
    private lateinit var viewModel: SymptomsViewModel
    private var time = ""
    private var symptoms = ""
    private var note = ""
    private val args :SymptomsFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSymptomsBinding.inflate(inflater, container, false)

        symptoms = this.args.symptoms
        binding.editTextSymptoms.setText(symptoms)

        binding.imageViewChooseSymptoms.setOnClickListener {
            findNavController().navigate(R.id.action_symptomsFragment_to_chooseSymptomsFragment)
        }

        binding.imageViewChooseTime.setOnClickListener {
            openTimePicker { selectedTime ->
                binding.editTextTime.setText(selectedTime)
                time = selectedTime
            }
        }
        binding.buttonSaveSymptoms.setOnClickListener {
            note = binding.editTextNoteSymptoms.text.toString()
            if (time.isNotEmpty() && symptoms.isNotEmpty()) {
                save(time, symptoms, note)
                Snackbar.make(it, R.string.snackbar2, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, R.string.snackbar_symptos, Snackbar.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun save(time: String, symptoms: String, note: String) {
        viewModel.saveSymptoms(time, symptoms, note)
    }

    private fun openTimePicker(callback: (String) -> Unit) {
        val isSystem24Hour = DateFormat.is24HourFormat(requireContext())
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
        val tempViewModel: SymptomsViewModel by viewModels()
        viewModel = tempViewModel
    }
}