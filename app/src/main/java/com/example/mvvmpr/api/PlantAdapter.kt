package com.example.mvvmpr.api

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpr.databinding.TextRawItemBinding

class PlantAdapter(val context: Context, private val dataSet: List<Plants>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: TextRawItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(plant: Plants){
            binding.textArea.text = plant.name
            Glide.with(context)
                .load(plant.imageUrl)
                .into(binding.imageArea)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextRawItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}