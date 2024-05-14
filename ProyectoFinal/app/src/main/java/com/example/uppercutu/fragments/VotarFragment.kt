// VotarFragment.kt
package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votacion
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.util.*
class VotarFragment : Fragment(), CustomCartaPuntuaje.DatosListener {

    private lateinit var votadosAdapter: VotadosAdapter
    private lateinit var rvVotar: RecyclerView
    private val exampleList = ArrayList<Votacion>() // Declaración de la lista de datos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_votar, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val addVotar: FloatingActionButton = view.findViewById(R.id.anadirVotacion)
        addVotar.setOnClickListener {
            val customCartaPuntuajeFragment = CustomCartaPuntuaje()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, customCartaPuntuajeFragment)
                .addToBackStack(null)
                .commit()
        }

        rvVotar = view.findViewById(R.id.recyclerVotar)
        rvVotar.layoutManager = LinearLayoutManager(requireContext())

        // Aquí es donde deberías obtener tus datos reales y asignarlos a exampleList
        // Por ejemplo:
        // exampleList = obtenerDatosReales()

        votadosAdapter = VotadosAdapter(exampleList)
        rvVotar.adapter = votadosAdapter

        val customCartaPuntuajeFragment = CustomCartaPuntuaje()
        customCartaPuntuajeFragment.datosListener = this
    }

    override fun onDatosEnviados(boxer1Name: String, boxer2Name: String, rounds: Int) {
        // Aquí recibes los datos desde CustomCartaPuntuaje
        // y los puedes utilizar para actualizar el RecyclerView
        val nuevaVotacion = Votacion(boxer1Name, boxer2Name, rounds)
        exampleList.add(nuevaVotacion) // Agregar la nueva votación a la lista
        votadosAdapter.notifyDataSetChanged() // Notificar al adaptador sobre el cambio en los datos
    }
}
