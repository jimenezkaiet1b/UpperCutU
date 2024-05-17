package com.example.uppercutu.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uppercutu.R
import com.example.uppercutu.api.ImageUrlStorage
import com.example.uppercutu.api.RetrofitInstance
import com.example.uppercutu.api.SearchResponse
import com.example.uppercutu.modelo.fighters.Fighter
import com.example.uppercutu.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FightersAdapter(private var fightersList: List<Fighter>, private val context: Context) :
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

        val storedImageUrl = currentItem.name?.let { ImageUrlStorage.getImageUrl(context, it) }

        if (storedImageUrl != null) {
            Glide.with(holder.itemView.context).load(storedImageUrl).into(holder.fighterImageView)
        } else {
            currentItem.name?.let { name ->
                RetrofitInstance.RetrofitInstance.api.searchImages(
                    Constants.API_KEYIMAGES,
                    Constants.CX_ID,
                    name
                ).enqueue(object : Callback<SearchResponse> {
                    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                        if (response.isSuccessful) {
                            val imageUrl = response.body()?.items?.firstOrNull()?.link ?: ""
                            currentItem.imageUrl = imageUrl
                            Glide.with(holder.itemView.context).load(imageUrl).into(holder.fighterImageView)
                            ImageUrlStorage.saveImageUrl(context, currentItem.name, imageUrl)
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        Log.e("FightersAdapter", "Error fetching image: ${t.message}")
                    }
                })
            }
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
