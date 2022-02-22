package com.bncc.habith.ui.view.habithall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.FragmentAllBinding
import com.bncc.habith.ui.adapter.HabithAdapter
import com.bncc.habith.ui.state.DataStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private val viewModel: AllViewModel by viewModels()
    private lateinit var habithAdapter: HabithAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(layoutInflater, container, false)
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
            recyclerAll.layoutManager = LinearLayoutManager(requireContext())
            recyclerAll.layoutAnimation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_fall_down
            )
            recyclerAll.adapter = habithAdapter

            swipeRefreshAll.setOnRefreshListener {
                viewModel.fetchHabith()
            }

            buttonNext.setOnClickListener {
                viewModel.pickDate(1)
            }

            buttonPrev.setOnClickListener {
                viewModel.pickDate(-1)
            }

            textDate.setOnClickListener {
                viewModel.pickDate(requireContext())
            }
        }
    }

    private fun subscribeLiveData() {
        viewModel.getHabith().observe(requireActivity()) {
            binding.viewState = it.status

            if (it.status == DataStatus.Status.SUCCESS) loadHabith(it.data!!)
        }
        viewModel.getDate().observe(requireActivity()) {
            binding.textDate.text = it
        }
    }

    private fun loadHabith(habits: List<HabithResponse.Data>) {
        habits.let {
            habithAdapter.clearHabits()
            habithAdapter.fillHabits(habits)
        }
    }

}