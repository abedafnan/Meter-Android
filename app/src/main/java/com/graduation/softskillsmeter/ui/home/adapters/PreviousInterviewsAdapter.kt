package com.graduation.softskillsmeter.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.softskillsmeter.databinding.ItemPreviousInterviewBinding
import com.graduation.softskillsmeter.models.Interview

class PreviousInterviewsAdapter(var data: List<Interview>)
    :RecyclerView.Adapter<PreviousInterviewsAdapter.PreviousInterviewsViewHolder>(){

    inner class PreviousInterviewsViewHolder(var itemPreviousInterviewBinding: ItemPreviousInterviewBinding) : RecyclerView.ViewHolder(itemPreviousInterviewBinding.root){
        fun bind(interview: Interview){
            itemPreviousInterviewBinding.interview = interview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousInterviewsViewHolder {
        val binding = ItemPreviousInterviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreviousInterviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousInterviewsViewHolder, position: Int) {
        val round = data[position]

        holder.bind(round)

        holder.itemPreviousInterviewBinding.ivArrowRight.setOnClickListener {
            //TODO: Open interview details
        }
    }

    override fun getItemCount() = data.size
}