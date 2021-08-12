package com.graduation.softskillsmeter.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.softskillsmeter.databinding.ItemPreviousInterviewBinding
import com.graduation.softskillsmeter.models.Interview

class PreviousInterviewsAdapter(var data: List<Interview>, val callback: OnItemClickListener)
    :RecyclerView.Adapter<PreviousInterviewsAdapter.PreviousInterviewsViewHolder>(){

    inner class PreviousInterviewsViewHolder(var itemPreviousInterviewBinding: ItemPreviousInterviewBinding) : RecyclerView.ViewHolder(itemPreviousInterviewBinding.root){
        fun bind(interview: Interview){
            itemPreviousInterviewBinding.interview = interview

            var score = interview.score

            if (score < 1.0) {
                score *= 10
            }

            val formattedScore = String.format("%.1f", score)
            itemPreviousInterviewBinding.tvScore.text = formattedScore
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
           callback.onItemClicked(position)
        }
    }

    override fun getItemCount() = data.size

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}