package com.bncc.habith.ui.view.habithongoing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.FragmentOngoingBinding
import com.bncc.habith.ui.adapter.HabithAdapter
import com.bncc.habith.ui.state.DataStatus
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
        initView()
        subscribeLiveData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchHabith()
    }

    private fun initView() {
        habithAdapter = HabithAdapter(requireContext())

        with(binding) {
            recyclerOngoing.layoutManager = LinearLayoutManager(requireContext())
            recyclerOngoing.layoutAnimation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_fall_down
            )
            recyclerOngoing.adapter = habithAdapter

            swipeRefreshOngoing.setOnRefreshListener {
                viewModel.fetchHabith()
            }
        }
    }

    private fun subscribeLiveData() {
        viewModel.getHabith().observe(requireActivity()) {
            binding.viewState = it.status

            if (it.status == DataStatus.Status.SUCCESS) loadHabith(it.data!!)
        }
    }

    private fun loadHabith(habits: List<HabithResponse.Data>) {
        habits.let {
            habithAdapter.clearHabits()
            habithAdapter.fillHabits(habits)
        }
    }

}