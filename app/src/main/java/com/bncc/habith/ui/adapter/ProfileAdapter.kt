package com.bncc.habith.ui.adapter

import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bncc.habith.databinding.ItemProfileListBinding

class ProfileAdapter(
    private val images: TypedArray,
    private val texts: Array<String>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemProfileListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int, text: String, listener: (String) -> Unit) = with(binding) {
            textProfile.text = text
            imageProfile.setBackgroundResource(image)
            itemView.setOnClickListener { listener(text) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProfileListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images.getResourceId(position, -1), texts[position], listener)
    }

    override fun getItemCount(): Int = texts.size

}