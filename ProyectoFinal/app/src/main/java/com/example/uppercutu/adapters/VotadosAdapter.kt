package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votados
import java.text.SimpleDateFormat
import java.util.*

class VotadosAdapter(private var votadosList: MutableList<Votados>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<VotadosAdapter.ViewHolder>() {
    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.itemList_nameTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.itemList_description)
        val fechaCreacionTextView: TextView =
            itemView.findViewById(R.id.itemList_creationDateTextView)

        init {
            // Otros códigos de inicialización de ViewHolder
            val deleteButton: ImageButton = itemView.findViewById(R.id.itemList_deleteBtn)
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    deleteItem(position)
                }
            }
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun getPositionForItem(item: Votados): Int {
            return votadosList.indexOf(item)
        }

        fun deleteItem(position: Int) {
            votadosList.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.votados_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return votadosList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = votadosList[position]

        holder.tituloTextView.text = currentItem.titulo
        holder.descripcionTextView.text = currentItem.descripcion
        holder.fechaCreacionTextView.text = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(currentItem.fechaCreacion)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}



