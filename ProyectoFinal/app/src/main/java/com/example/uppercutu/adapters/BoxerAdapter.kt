package com.example.uppercutu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Pugiles
import com.squareup.picasso.Picasso


class BoxerAdapter(private val context: Context?, private val boxers: ArrayList<Pugiles>) :
    RecyclerView.Adapter<BoxerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_boxers, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return boxers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val boxer = boxers[position]
        Picasso.get().load(boxer.url).into(holder.boxerImage as ImageView)


        // Kenburns animacion
//        holder.boxerImage.resume()

        holder.boxerTitle.text = boxer.nombre
        holder.boxerDivision.text = boxer.division
        holder.boxerRating.rating = boxer.rating
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val boxerImage: TextView = itemView.findViewById(R.id.boxerImage)
        val boxerTitle: TextView = itemView.findViewById(R.id.boxerTitle)
        val boxerDivision: TextView = itemView.findViewById(R.id.boxerDivision)
        val boxerRating: RatingBar = itemView.findViewById(R.id.boxerRating)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // Handle click event
        }
    }
}
