package com.example.uppercutu.fragments

import CustomCartaPuntuaje
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votados
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class VotarFragment : Fragment(), CustomCartaPuntuaje.OnSaveClickListener {

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
            // Abrir el fragmento para ingresar los datos
            val customCartaPuntuajeFragment = CustomCartaPuntuaje()
            customCartaPuntuajeFragment.setOnSaveClickListener(this)
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

    override fun onSaveClicked(boxer1Name: String, boxer2Name: String, numberOfRounds: Int) {
        // Agregar los datos a la lista de votados
        val nuevoVotado = Votados("$boxer1Name vs $boxer2Name", "Número de rondas: $numberOfRounds", Date())
        votadosList.add(nuevoVotado)
        // Notificar al adaptador que se ha agregado un nuevo elemento
        votadosAdapter.notifyItemInserted(votadosList.size - 1)
        // Desplazarse al nuevo elemento agregado
        rvVotar.scrollToPosition(votadosList.size - 1)
    }
}
