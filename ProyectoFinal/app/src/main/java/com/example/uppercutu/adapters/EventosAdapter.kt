package com.example.uppercutu.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.modelo.events.EventosItem
import com.example.uppercutu.modelo.news.Article
import com.google.type.DateTime

class EventosAdapter(
    private val context: Context,
    private var eventos: List<EventosItem>
) : RecyclerView.Adapter<EventosAdapter.ViewHolder>() {

    private var eventosFiltrados: List<EventosItem> = eventos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.horarios_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = eventosFiltrados[position]
        holder.eventoNombreTextView.text = evento.Name
        holder.fechaTextView.text = evento.DateTime
        holder.shortNameTextView.text = evento.ShortName
    }

    override fun getItemCount(): Int {
        return eventosFiltrados.size
    }

    fun updateEvents(eventos: List<EventosItem>) {
        this.eventos = eventos
        this.eventosFiltrados = eventos
        notifyDataSetChanged()
    }

    fun filtrar(texto: String) {
        eventosFiltrados = if (texto.isEmpty()) {
            eventos
        } else {
            eventos.filter {
                it.Name.contains(texto, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventoNombreTextView: TextView = itemView.findViewById(R.id.even_name)
        val fechaTextView: TextView = itemView.findViewById(R.id.dateTimeEvento)
        val shortNameTextView: TextView = itemView.findViewById(R.id.shortName)
    }
}

