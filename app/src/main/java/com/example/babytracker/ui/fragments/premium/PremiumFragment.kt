package com.example.babytracker.ui.fragments.premium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentPremiumBinding

class PremiumFragment : Fragment() {
    private lateinit var binding: FragmentPremiumBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)
        binding.apply {
            buttonPremium.setOnClickListener {
                findNavController().navigate(R.id.action_premiumFragment_to_homeFragment)
            }
        }
        return binding.root
    }
}