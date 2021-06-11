package com.graduation.softskillsmeter.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.softskillsmeter.databinding.ItemInstructionsBinding
import com.graduation.softskillsmeter.databinding.ItemPreviousInterviewBinding
import com.graduation.softskillsmeter.models.Instruction

class InstructionsAdapter(var data: List<Instruction>)
    :RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>(){

    inner class InstructionsViewHolder(var itemInstructionBinding: ItemInstructionsBinding) : RecyclerView.ViewHolder(itemInstructionBinding.root){
        fun bind(instruction: Instruction){
            itemInstructionBinding.instruction = instruction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val binding = ItemInstructionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        val round = data[position]
        holder.bind(round)
    }

    override fun getItemCount() = data.size
}