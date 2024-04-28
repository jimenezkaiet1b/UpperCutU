package com.example.uppercutu.fragments

import VotadosAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.data.Votados
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VotarFragment : Fragment(){

    private lateinit var votadosAdapter: VotadosAdapter
    private lateinit var rvVotar: RecyclerView
    private val votadosList = mutableListOf<Votados>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_votar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val addVotar: FloatingActionButton = view.findViewById(R.id.anadirVotacion)
        addVotar.setOnClickListener {
            // Crear una instancia del fragmento CustomCartaPuntuaje
            val customCartaPuntuajeFragment = CustomCartaPuntuaje()
            // Reemplazar el fragmento actual con CustomCartaPuntuaje
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, customCartaPuntuajeFragment)
                .addToBackStack(null)
                .commit()
        }

        // Inicializar el RecyclerView y el adaptador
        votadosAdapter = VotadosAdapter(votadosList)
        rvVotar = view.findViewById(R.id.recyclerVotar)
        rvVotar.layoutManager = LinearLayoutManager(context)
        rvVotar.adapter = votadosAdapter
    }

}
