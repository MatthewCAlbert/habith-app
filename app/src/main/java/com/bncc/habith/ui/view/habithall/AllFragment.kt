package com.bncc.habith.ui.view.habithall

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.FragmentAllBinding
import com.bncc.habith.ui.view.detail.DetailActivity
import com.bncc.habith.ui.adapter.HabithAdapter
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


    private fun initView(){
        habithAdapter = HabithAdapter(requireContext())

        with(binding) {
            recyclerAll.layoutManager = LinearLayoutManager(requireContext())
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

    private fun subscribeLiveData(){
        viewModel.getHabith().observe(requireActivity()) {
            loadHabith(it!!)
        }

        viewModel.getDate().observe(requireActivity()) {
            binding.textDate.text = it
        }
    }

    private fun loadHabith(habits: List<HabithResponse>) {
        habits.let {
            habithAdapter.clearHabits()
            habithAdapter.fillHabits(habits)
        }
    }

}