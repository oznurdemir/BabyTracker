package com.example.babytracker.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsItemAdapter: SettingsItemAdapter
    private lateinit var viewModel: SettingsViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.apply {
            imageViewSettingsBack.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
            }

            buttonForwardPremium.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_premiumFragment)
            }

            recyclerViewSettingsItem.layoutManager = LinearLayoutManager(requireContext())
            viewModel.settingsItemList.observe(viewLifecycleOwner){
                settingsItemAdapter = SettingsItemAdapter(it, requireContext())
                recyclerViewSettingsItem.adapter = settingsItemAdapter
            }
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SettingsViewModel by viewModels()
        viewModel = tempViewModel
    }
}