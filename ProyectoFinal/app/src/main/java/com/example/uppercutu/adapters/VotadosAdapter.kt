package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votados
import java.text.SimpleDateFormat
import java.util.*

class VotadosAdapter(private val votadosList: List<Votados>) :
    RecyclerView.Adapter<VotadosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.itemList_nameTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.itemList_description)
        val fechaCreacionTextView: TextView = itemView.findViewById(R.id.itemList_creationDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.votados_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = votadosList[position]

        holder.tituloTextView.text = currentItem.titulo
        holder.descripcionTextView.text = currentItem.descripcion
        holder.fechaCreacionTextView.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentItem.fechaCreacion)


    }

    override fun getItemCount() = votadosList.size
}
