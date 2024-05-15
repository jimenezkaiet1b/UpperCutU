package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votacion

class VotacionAdapter(
    private val votaciones: List<Votacion>,
    private val onItemClicked: (Votacion) -> Unit,
    private val onItemDeleted: (Int) -> Unit
) : RecyclerView.Adapter<VotacionAdapter.VotacionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_votacion, parent, false)
        return VotacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: VotacionViewHolder, position: Int) {
        val votacion = votaciones[position]
        holder.bind(votacion)


    }

    override fun getItemCount(): Int = votaciones.size

    inner class VotacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val boxer1NameTextView: TextView = itemView.findViewById(R.id.boxer1Name)
        private val boxer2NameTextView: TextView = itemView.findViewById(R.id.boxer2Name)
        private val roundsTextView: TextView = itemView.findViewById(R.id.numberOfRoundsTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.itemList_deleteBtn)

        fun bind(votacion: Votacion) {
            boxer1NameTextView.text = votacion.boxer1Name
            boxer2NameTextView.text = votacion.boxer2Name
            roundsTextView.text = votacion.numberOfRounds.toString()


            itemView.setOnClickListener {
                onItemClicked(votacion)
            }

            deleteButton.setOnClickListener {
                onItemDeleted(adapterPosition)
            }
        }
    }
}
