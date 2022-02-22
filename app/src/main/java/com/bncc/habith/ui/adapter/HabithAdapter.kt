package com.bncc.habith.ui.adapter

import android.content.Context
import android.content.Intent
import android.provider.Contacts.SettingsColumns.KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ItemOngoingListBinding
import com.bncc.habith.ui.view.detail.DetailActivity

class HabithAdapter(
    private val context: Context
) : RecyclerView.Adapter<HabithAdapter.ViewHolder>() {

    private var habits: List<HabithResponse.Data> = ArrayList()

    fun clearHabits() {
        this.habits = emptyList()
    }

    fun fillHabits(habits: List<HabithResponse.Data>) {
        this.habits = habits
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemOngoingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(context: Context, habith: HabithResponse.Data) {
            with(binding) {
                textTitle.text = habith.title
                textCategory.text = habith.category
                textTargetNum.text = habith.target.toString()
                textRepeatTime.visibility = View.GONE
                textDate.text = "${habith.start} - ${habith.end}"
                textRepeatNum.text = "Repeat ${habith.repeat_every_day} time(s)"

                if (habith.start == null) textDate.visibility = View.GONE
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.KEY, habith)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOngoingListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(context, habits[position])
    }

    override fun getItemCount(): Int = habits.size
}