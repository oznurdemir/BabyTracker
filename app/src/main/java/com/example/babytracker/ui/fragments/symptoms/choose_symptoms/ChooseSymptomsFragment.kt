package com.example.babytracker.ui.fragments.symptoms.choose_symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.babytracker.databinding.FragmentChooseSymptomsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseSymptomsFragment : Fragment() {
    private lateinit var binding: FragmentChooseSymptomsBinding
    private lateinit var viewModel: ChooseSymptomsViewModel
    private lateinit var adapter: ChooseSymptomsAdapter
    private var chosenSymptomsList = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChooseSymptomsBinding.inflate(inflater, container, false)
        binding.symptomsRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        lifecycleScope.launchWhenStarted {
            viewModel.chooseSymptomsList.collect{
                adapter = ChooseSymptomsAdapter(it, object : SymptomsClickedListener{
                    override fun onSymptomsClicked(symptoms: String) {
                        chosenSymptomsList.add(symptoms)
                    }
                })
                binding.symptomsRv.adapter = adapter
            }
        }
        binding.buttonChooseSymptoms.setOnClickListener {
            val symptomsString = chosenSymptomsList.toString()
            val cleanedString = symptomsString.substring(1, symptomsString.length - 1) // listedeki köşeli parantezleri kaldırıyoruz.
            val action = ChooseSymptomsFragmentDirections.actionChooseSymptomsFragmentToSymptomsFragment(cleanedString)
            findNavController().navigate(action)
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ChooseSymptomsViewModel by viewModels()
        viewModel = tempViewModel
    }
}