package com.kkp.gymworkoutplan.frags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.api.LogDescriptor
import com.kkp.gymworkoutplan.R
import com.kkp.gymworkoutplan.WorkoutViewModel
import com.kkp.gymworkoutplan.databinding.FragmentMixBinding
import kotlinx.coroutines.flow.collectLatest

class MixFragment : Fragment() {
    private lateinit var _binding: FragmentMixBinding
    private val binding get() = _binding!!
    private lateinit var viewModel : WorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(WorkoutViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.buddyState.collectLatest {
            // TODO: Tu wprowadzic funkcjonalnosci
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMixBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }




    companion object {
    }
}