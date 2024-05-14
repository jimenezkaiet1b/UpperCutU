package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votacion
import com.example.uppercutu.fragments.VotarFragment



class VotadosAdapter(private val votaciones: MutableList<Votacion>) : RecyclerView.Adapter<VotadosAdapter.VotacionViewHolder>() {

    inner class VotacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val boxer1NameTextView: TextView = itemView.findViewById(R.id.boxer1Name)
        val boxer2NameTextView: TextView = itemView.findViewById(R.id.boxer2_name)
        val numberOfRoundsTextView: TextView = itemView.findViewById(R.id.numberOfRoundsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotacionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.votados_list, parent, false)
        return VotacionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VotacionViewHolder, position: Int) {
        val currentVotacion = votaciones[position]
        holder.boxer1NameTextView.text = currentVotacion.boxer1Name
        holder.boxer2NameTextView.text = currentVotacion.boxer2Name
        holder.numberOfRoundsTextView.text = currentVotacion.numberOfRounds.toString()
    }

    override fun getItemCount() = votaciones.size

    fun agregarVotacion(votacion: Votacion) {
        votaciones.add(votacion)
        notifyItemInserted(votaciones.size - 1) // Notifica al adaptador sobre la inserción en la última posición
    }

}

