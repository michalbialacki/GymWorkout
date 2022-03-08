package com.kkp.gymworkoutplan.frags

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kkp.gymworkoutplan.Adapters.PplExecAdapter
import com.kkp.gymworkoutplan.WorkoutInter
import com.kkp.gymworkoutplan.WorkoutViewModel
import com.kkp.gymworkoutplan.databinding.FragmentPplFramgentBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class PplFramgent : Fragment(), WorkoutInter {
    private lateinit var _binding : FragmentPplFramgentBinding
    private val binding get() = _binding
    private lateinit var viewModel : WorkoutViewModel
    val TAG = "Fragment PPL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(WorkoutViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPplFramgentBinding.inflate(inflater,container,false)
        val view = binding.root
        var exerList = viewModel.exerList.value
        val adapter = PplExecAdapter(exerList,this)
        binding.rvExer.adapter = adapter
        binding.rvExer.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.Main){
                viewModel.buddyState.collectLatest {
                    delay(100L)
                    var member = viewModel.membersList.value[it]
                    exerList.forEachIndexed { index, pplExec ->
                        pplExec.mass = member.chestPressPR
                        adapter.notifyItemChanged(index)
                    }
                    binding.tvPplText.text = "${member}"
                }
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onPause() {
        super.onPause()
    }

    companion object {

    }



    override fun onExcerciseClicked(position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.updatePosition(position)
            Log.d("CHECKBUTTON", "$position")
            viewModel.updateExeList(position)
        }
    }
}