package com.example.uppercutu.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.modelo.events.EventosItem
import com.example.uppercutu.modelo.news.Article

class EventosAdapter(private val context: Context, private var eventos: List<EventosItem>) :
    RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {
    private var filteredEvents: List<EventosItem> = eventos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horarios_list, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.even_name)
        private val dateTimeTextView: TextView = itemView.findViewById(R.id.dateTimeEvento)
        private val shortNameTextView: TextView = itemView.findViewById(R.id.shortName)
        private val urlImageView: ImageView = itemView.findViewById(R.id.urlImage)

        fun bind(evento: EventosItem) {
            nameTextView.text = evento.Name
            dateTimeTextView.text = evento.DateTime
            shortNameTextView.text = evento.ShortName

        }
    }

    fun updateEvents(events: List<EventosItem>) {
        this.eventos = events
        notifyDataSetChanged()
    }

    fun filtrar(query: String) {
        filteredEvents = if (query.isEmpty()) {
            eventos
        } else {
            eventos.filter { evento ->
                evento.ShortName.contains(query, ignoreCase = true) ||
                        evento.Name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }



}
