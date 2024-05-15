package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotacionAdapter
import com.example.uppercutu.adapters.VotacionesAdapter
import com.example.uppercutu.data.Votacion

class VotandoFragment : Fragment() {

    private lateinit var goBackButton: ImageButton
    private lateinit var boxer1NameTextView: TextView
    private lateinit var boxer2NameTextView: TextView
    private lateinit var resulBox1TextView: TextView
    private lateinit var resulBox2TextView: TextView
    private lateinit var votacionesRecyclerView: RecyclerView
    private  lateinit var btnVotarbox1:Button
    private  lateinit var btnVotarbox2:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_votando, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asignar las vistas
        goBackButton = view.findViewById(R.id.goBack)
        boxer1NameTextView = view.findViewById(R.id.box1nombre)
        boxer2NameTextView = view.findViewById(R.id.box2nombre)
        resulBox1TextView = view.findViewById(R.id.resulBox1)
        resulBox2TextView = view.findViewById(R.id.resulBox2)
        votacionesRecyclerView = view.findViewById(R.id.votacionesRv)

        var rounds = 0

        votacionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        votacionesRecyclerView.adapter = VotacionAdapter(getData(), { /* nada */ }, { /* nada */ })

        arguments?.let {
            val boxer1Name = it.getString("boxer1Name")
            val boxer2Name = it.getString("boxer2Name")
            val resulBox1 = it.getInt("resulBox1")
            val resulBox2 = it.getInt("resulBox2")
            rounds = it.getInt("rounds")

            boxer1NameTextView.text = boxer1Name
            boxer2NameTextView.text = boxer2Name
            resulBox1TextView.text = resulBox1.toString()
            resulBox2TextView.text = resulBox2.toString()
        }

        val adapter = VotacionesAdapter(rounds)
        votacionesRecyclerView.adapter = adapter



        goBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getData(): List<Votacion> {
        return listOf()
    }
}
