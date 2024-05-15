package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votacion

class VotadosAdapter(
    private val votaciones: MutableList<Votacion>,
    private val onItemClick: (Votacion) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<VotadosAdapter.VotadosViewHolder>() {

    inner class VotadosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val boxer1Name: TextView = itemView.findViewById(R.id.boxer1Name)
        val boxer2Name: TextView = itemView.findViewById(R.id.boxer2Name)
        val rounds: TextView = itemView.findViewById(R.id.numberOfRoundsTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.itemList_deleteBtn)

        init {
            itemView.setOnClickListener {
                onItemClick(votaciones[adapterPosition])
            }
            deleteButton.setOnClickListener {
                onDeleteClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotadosViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_votacion, parent, false)
        return VotadosViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VotadosViewHolder, position: Int) {
        val currentItem = votaciones[position]
        holder.boxer1Name.text ="Peleador 1: "+ currentItem.boxer1Name
        holder.boxer2Name.text ="Peleador 2: "+ currentItem.boxer2Name
        holder.rounds.text ="Rondas: "+ currentItem.numberOfRounds.toString()
    }

    override fun getItemCount() = votaciones.size
}
