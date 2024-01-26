package com.example.babytracker.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentFirstPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstPageFragment : Fragment() {
    private lateinit var binding : FragmentFirstPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFirstPageBinding.inflate(inflater, container, false)
        binding.apply {
            buttonOnboarding1.setOnClickListener {
                findNavController().navigate(R.id.action_firstPageFragment_to_secondPageFragment)
            }
        }
        return binding.root
    }
}