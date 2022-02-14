package com.bncc.habith.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.FragmentAllBinding
import com.bncc.habith.ui.home.adapter.HabithAdapter
import com.bncc.habith.ui.home.viewmodel.AllViewModel

class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private val viewModel: AllViewModel by viewModels()
    private lateinit var habithAdapter: HabithAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(layoutInflater, container, false)

        viewModel.fetchHabith()
        habithAdapter = HabithAdapter {
            //todo navigate to DetailActivity
        }

        with(binding) {
            recyclerAll.layoutManager = LinearLayoutManager(requireContext())
            recyclerAll.adapter = habithAdapter

            swipeRefreshAll.setOnRefreshListener {
                viewModel.fetchHabith()
            }
        }

        viewModel.getHabith().observe(requireActivity()) {
            loadHabith(it!!)
        }

        return binding.root
    }

    private fun loadHabith(habits: List<HabithResponse>) {
        habits.let {
            habithAdapter.clearHabits()
            habithAdapter.fillHabits(habits)
        }
    }

}