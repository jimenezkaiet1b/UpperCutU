package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
class VotacionesAdapter(private val rounds: Int) : RecyclerView.Adapter<VotacionesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_votacion_round, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roundTextView.text = "Round ${position + 1}"

        holder.button1.text = holder.button1Value.toString()
        holder.button2.text = holder.button2Value.toString()

        var isAdding1 = true
        var isAdding2 = true

        holder.button1.setOnClickListener {
            if (isAdding1) {
                if (holder.button1Value < 10) {
                    holder.button1Value++
                    holder.button1.text = holder.button1Value.toString()
                } else {
                    isAdding1 = false
                }
            } else {
                if (holder.button1Value > 6) {
                    holder.button1Value--
                    holder.button1.text = holder.button1Value.toString()
                } else {
                    isAdding1 = true
                }
            }
        }

        holder.button2.setOnClickListener {
            if (isAdding2) {
                if (holder.button2Value < 10) {
                    holder.button2Value++
                    holder.button2.text = holder.button2Value.toString()
                } else {
                    isAdding2 = false
                }
            } else {
                if (holder.button2Value > 6) {
                    holder.button2Value--
                    holder.button2.text = holder.button2Value.toString()
                } else {
                    isAdding2 = true
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return rounds
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roundTextView: TextView = itemView.findViewById(R.id.rounds)
        val button1: Button = itemView.findViewById(R.id.votacion1)
        val button2: Button = itemView.findViewById(R.id.votacion2)

        var button1Value = 0
        var button2Value = 0
    }
}
