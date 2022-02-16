package com.bncc.habith.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.FragmentOngoingBinding
import com.bncc.habith.ui.home.adapter.HabithAdapter
import com.bncc.habith.ui.home.viewmodel.OngoingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OngoingFragment : Fragment() {

    private lateinit var binding: FragmentOngoingBinding
    private val viewModel: OngoingViewModel by viewModels()
    private lateinit var habithAdapter: HabithAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOngoingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchHabith()

        initView()
        subscribeLiveData()
    }

    private fun initView() {
        habithAdapter = HabithAdapter {
            //todo navigate to DetailActivity
        }

        with(binding) {
            recyclerOngoing.layoutManager = LinearLayoutManager(requireContext())
            recyclerOngoing.adapter = habithAdapter

            swipeRefreshOngoing.setOnRefreshListener {
                viewModel.fetchHabith()
            }
        }
    }

    private fun subscribeLiveData() {
        viewModel.getHabith().observe(requireActivity()) {
            loadHabith(it!!)
        }
    }

    private fun loadHabith(habits: List<HabithResponse>) {
        habits.let {
            habithAdapter.clearHabits()
            habithAdapter.fillHabits(habits)
        }
    }

}