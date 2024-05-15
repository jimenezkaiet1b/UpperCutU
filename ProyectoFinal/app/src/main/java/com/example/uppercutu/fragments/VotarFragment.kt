package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votacion
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VotarFragment : Fragment(), CustomCartaPuntuaje.DatosListener {

    private lateinit var votadosAdapter: VotadosAdapter
    private lateinit var rvVotar: RecyclerView
    private val exampleList = ArrayList<Votacion>()

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
            val customCartaPuntuajeFragment = CustomCartaPuntuaje()
            customCartaPuntuajeFragment.datosListener = this
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, customCartaPuntuajeFragment)
                .addToBackStack(null)
                .commit()
        }

        rvVotar = view.findViewById(R.id.recyclerVotar)
        rvVotar.layoutManager = LinearLayoutManager(requireContext())

        votadosAdapter = VotadosAdapter(exampleList,
            { votacion -> // Navegar a VotandoFragment y pasar los datos de la votación seleccionada
                val votandoFragment = VotandoFragment()
                val bundle = Bundle()
                bundle.putString("boxer1Name", votacion.boxer1Name)
                bundle.putString("boxer2Name", votacion.boxer2Name)
                bundle.putInt("rounds", votacion.numberOfRounds)
                votandoFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, votandoFragment)
                    .addToBackStack(null)
                    .commit()
            },
            { position -> // Eliminar el elemento de la lista y notificar al adaptador
                exampleList.removeAt(position)
                votadosAdapter.notifyItemRemoved(position)
            }
        )
        rvVotar.adapter = votadosAdapter
    }

    override fun onDatosEnviados(boxer1Name: String, boxer2Name: String, rounds: Int) {
        val nuevaVotacion = Votacion(boxer1Name, boxer2Name, rounds)
        exampleList.add(nuevaVotacion)
        votadosAdapter.notifyDataSetChanged()
    }
}
