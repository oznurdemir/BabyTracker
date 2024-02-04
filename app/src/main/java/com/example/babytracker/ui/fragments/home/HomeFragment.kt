package com.example.babytracker.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            imageViewSettings.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            imageViewCalendar.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_calenderFragment)
            }
            buttonFeeding.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_feedingFragment)
            }
            buttonSleep.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_sleepFragment)
            }
            buttonSymptoms.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSymptomsFragment("")
                findNavController().navigate(action)
            }
            imageViewCalendar.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_calenderFragment)
            }
        }
        return binding.root
    }
}