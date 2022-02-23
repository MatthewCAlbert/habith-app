package com.bncc.habith.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ItemOngoingListBinding
import com.bncc.habith.ui.view.detail.DetailActivity
import com.bncc.habith.util.InputHelper.fixedDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

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
        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(context: Context, habith: HabithResponse.Data) {
            with(binding) {
                textTitle.text = habith.title
                textCategory.text = habith.category
                textTargetNum.text = habith.target.toString()
                textRepeatTime.visibility = View.GONE

                if (habith.start != null && habith.end != null) {
                    val fixStart = fixedDate(habith.start)
                    val fixEnd = fixedDate(habith.end)
                    textDate.text = "$fixStart - $fixEnd"

                } else textDate.visibility = View.GONE

                textRepeatNum.text = "Repeat ${habith.repeat_every_day} time(s)"
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(context, habits[position])
    }

    override fun getItemCount(): Int = habits.size
}