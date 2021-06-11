package com.graduation.softskillsmeter.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.softskillsmeter.databinding.ItemFeedbackBinding
import com.graduation.softskillsmeter.models.Feedback

class FeedbackAdapter(var data: List<Feedback>, var isDetailed: Boolean, var callback: OnItemClickListener?)
    :RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>(){

    inner class FeedbackViewHolder(var itemInstructionBinding: ItemFeedbackBinding) : RecyclerView.ViewHolder(itemInstructionBinding.root){
        fun bind(feedback: Feedback){
            itemInstructionBinding.feedback = feedback

            if (!isDetailed)
                itemInstructionBinding.tvViewQuestion.visibility = View.GONE

            itemInstructionBinding.tvViewQuestion.setOnClickListener {
                callback?.onItemClicked()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val binding = ItemFeedbackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedbackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        val round = data[position]
        holder.bind(round)
    }

    override fun getItemCount() = data.size

    interface OnItemClickListener {
        fun onItemClicked()
    }
}