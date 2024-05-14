package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R

class VotacionAdapter(private val rounds: Int) :
    RecyclerView.Adapter<VotacionAdapter.VotacionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotacionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rondasavotar_list, parent, false)
        return VotacionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VotacionViewHolder, position: Int) {
        holder.bind(position + 1) // Sumamos 1 para empezar desde el round 1 en lugar de 0
    }

    override fun getItemCount(): Int {
        return rounds
    }

    inner class VotacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val votacionBox1: TextView = itemView.findViewById(R.id.votacionbox1)
        private val votacionBox2: TextView = itemView.findViewById(R.id.votacionbox2)
        private val roundsTextView: TextView = itemView.findViewById(R.id.rounds)

        fun bind(roundNumber: Int) {
            roundsTextView.text = roundNumber.toString()
            // Aquí puedes establecer el puntaje de votación para cada boxeador según sea necesario
            // Por ahora, estamos estableciendo ambos puntajes en 0 como ejemplo
            votacionBox1.text = "0"
            votacionBox2.text = "0"
        }
    }
}