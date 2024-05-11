package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotacionAdapter
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votados
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class VotandoFragment : Fragment() {
    private  lateinit var  votacionAdapter: VotacionAdapter
    private lateinit var rvVotar: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_votando, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)

        val rounds = arguments?.getInt("numberOfRounds") ?: 0
        recyclerViewSetUp(rounds)
    }


    private fun setup(view: View) {
        val boxer1Name = arguments?.getString("boxer1Name")
        val boxer2Name = arguments?.getString("boxer2Name")

        view.findViewById<TextView>(R.id.box1nombre).text = boxer1Name
        view.findViewById<TextView>(R.id.box2nombre).text = boxer2Name

    }


    private fun recyclerViewSetUp(rounds: Int) {
        // Obtén una referencia al RecyclerView desde el layout
        rvVotar = view?.findViewById(R.id.votacionesRv) ?: return

        // Crea una instancia del adaptador VotacionAdapter
        votacionAdapter = VotacionAdapter(rounds)

        // Establece un LayoutManager en el RecyclerView
        rvVotar.layoutManager = LinearLayoutManager(requireContext())

        // Establece el adaptador en el RecyclerView
        rvVotar.adapter = votacionAdapter
    }


}