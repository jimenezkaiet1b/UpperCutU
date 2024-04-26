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
import java.util.Date

class VotarFragment : Fragment() {
    private lateinit var votadosAdapter: VotadosAdapter
    private lateinit var rvVotar: RecyclerView
    private val votadosList = mutableListOf<Votados>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_votar, container, false)
    }
    /**
     * Configura las vistas tras haberse creado la vista del fragmento
     *
     * @param view La vista creada
     * @param savedInstanceState Informacion del estado previamente guardado
     *
     **/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)

    }

    private fun setup(view : View) {
        var addVotar: FloatingActionButton = view.findViewById(R.id.anadirVotacion)


        // Inicializar el RecyclerView y el adaptador
        votadosAdapter = VotadosAdapter(votadosList)
        rvVotar = view.findViewById(R.id.recyclerVotar)


        rvVotar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvVotar.adapter = votadosAdapter

        // Configurar el botón flotante para agregar elementos a la lista
        addVotar.setOnClickListener {
            // Aquí puedes agregar la lógica para crear un nuevo elemento Votados
            // Por ejemplo:
            val nuevoVotado = Votados("Nuevo título", "Nueva descripción", Date())
            // Agregar el nuevo elemento a la lista
            votadosList.add(nuevoVotado)
            // Notificar al adaptador que se agregó un nuevo elemento
            votadosAdapter.notifyItemInserted(votadosList.size - 1)
            // Desplazarse hasta la posición del nuevo elemento
            rvVotar.smoothScrollToPosition(votadosList.size - 1)


        }


    }
}