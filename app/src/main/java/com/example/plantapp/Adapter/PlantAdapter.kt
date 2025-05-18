package com.example.plantapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.Model.PlantModel
import com.example.plantapp.Model.PlantType
import com.example.plantapp.R
import com.bumptech.glide.request.target.Target


class PlantAdapter(private var plantList: List<PlantModel>) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CATEGORY = 0
        private const val VIEW_TYPE_QUESTION = 1
    }

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPlantName: TextView = itemView.findViewById(R.id.textPlantName)
        val imagePlant: ImageView = itemView.findViewById(R.id.imagePlant)
    }

    override fun getItemViewType(position: Int): Int {
        return when (plantList[position].type) {
            PlantType.CATEGORY -> VIEW_TYPE_CATEGORY
            PlantType.QUESTION -> VIEW_TYPE_QUESTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val layoutRes = when (viewType) {
            VIEW_TYPE_QUESTION -> R.layout.item_question_card
            else -> R.layout.item_plant_card
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plantList[position]
        holder.textPlantName.text = plant.name

        Glide.with(holder.itemView.context)
            .load(plant.imageUrl)
            .override(Target.SIZE_ORIGINAL)
            .into(holder.imagePlant)
    }

    override fun getItemCount(): Int = plantList.size

    fun submitList(newList: List<PlantModel>) {
        plantList = newList
        notifyDataSetChanged()
    }
}

