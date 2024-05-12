package com.example.uppercutu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.modelo.events.EventosItem

class EventosAdapter(private val context: Context, private var eventos: List<EventosItem>) :
    RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

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

            // Aquí podrías cargar la imagen del evento si fuera necesario
            // Puedes usar una biblioteca como Picasso o Glide para cargar imágenes desde URL
            // Ejemplo: Picasso.get().load(evento.imageUrl).into(urlImageView)
        }
    }

    // Este método actualiza la lista de eventos y notifica al RecyclerView sobre los cambios
    fun updateEvents(events: List<EventosItem>) {
        this.eventos = events
        notifyDataSetChanged()
    }
}
