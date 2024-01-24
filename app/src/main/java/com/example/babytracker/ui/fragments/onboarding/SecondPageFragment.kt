    package com.example.babytracker.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentSecondPageBinding

    class SecondPageFragment : Fragment() {
    private lateinit var binding: FragmentSecondPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondPageBinding.inflate(inflater, container, false)
        binding.apply {
            buttonOnboarding2.setOnClickListener {
                findNavController().navigate(R.id.action_secondPageFragment_to_premiumFragment)
            }
        }
        return binding.root
    }
}