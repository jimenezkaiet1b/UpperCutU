package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uppercutu.R
import com.example.uppercutu.api.RetrofitInstance
import com.example.uppercutu.api.SearchResponse
import com.example.uppercutu.modelo.fighters.Fighter
import com.example.uppercutu.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FightersAdapter(private var fightersList: List<Fighter>) :
    RecyclerView.Adapter<FightersAdapter.FightersViewHolder>() {

    class FightersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fighterNameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val fighterRankTextView: TextView = itemView.findViewById(R.id.ranking)
        val fighterWeightClassTextView: TextView = itemView.findViewById(R.id.weightTextView)
        val fighterDateTextView: TextView = itemView.findViewById(R.id.date)
        val fighterImageView: ImageView = itemView.findViewById(R.id.fighterImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FightersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fighter_list, parent, false)
        return FightersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FightersViewHolder, position: Int) {
        val currentItem = fightersList[position]
        holder.fighterNameTextView.text = currentItem.name
        holder.fighterRankTextView.text = currentItem.rank
        holder.fighterWeightClassTextView.text = currentItem.weightClass
        holder.fighterDateTextView.text = currentItem.date

        // Load image
        currentItem.imageUrl?.let {
            Glide.with(holder.itemView.context).load(it).into(holder.fighterImageView)
        }
    }

    override fun getItemCount(): Int {
        return fightersList.size
    }

    fun updateFighters(fighters: List<Fighter>) {
        fightersList = fighters
        notifyDataSetChanged()
    }
}
