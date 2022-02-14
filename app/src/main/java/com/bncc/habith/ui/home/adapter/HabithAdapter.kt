package com.bncc.habith.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ItemOngoingListBinding

class HabithAdapter(
    private val listener: (HabithResponse) -> Unit
) : RecyclerView.Adapter<HabithAdapter.ViewHolder>() {

    private var habits: List<HabithResponse> = ArrayList()

    fun clearHabits() {
        this.habits = emptyList()
    }

    fun fillHabits(habits: List<HabithResponse>) {
        this.habits = habits
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemOngoingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(listener: (HabithResponse) -> Unit, habith: HabithResponse) {
            with(binding) {
                textTitle.text = habith.title
                textCategory.text = habith.category
            }
            itemView.setOnClickListener { listener(habith) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOngoingListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listener, habits[position])
    }

    override fun getItemCount(): Int = habits.size
}