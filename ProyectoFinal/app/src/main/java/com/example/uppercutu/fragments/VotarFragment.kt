// VotarFragment.kt
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
import com.example.uppercutu.data.Votados
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class VotarFragment : Fragment() {

    private lateinit var votadosAdapter: VotadosAdapter
    private lateinit var rvVotar: RecyclerView
    private val votadosList = mutableListOf<Votados>()

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
            customCartaPuntuajeFragment.setAdapterAndList(votadosAdapter, votadosList)
            customCartaPuntuajeFragment.setOnSaveClickListener(object :
                CustomCartaPuntuaje.OnSaveClickListener {
                override fun onSaveClicked(boxer1Name: String, boxer2Name: String, numberOfRounds: Int) {
                    val nuevoVotado = Votados(
                        "$boxer1Name vs $boxer2Name",
                        "Número de rondas: $numberOfRounds",
                        Date()
                    )
                    votadosList.add(nuevoVotado)
                    votadosAdapter.notifyItemInserted(votadosList.size - 1) // Notificar al adaptador sobre el nuevo elemento
                    rvVotar.scrollToPosition(votadosList.size - 1)
                }
            })

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, customCartaPuntuajeFragment)
                .addToBackStack(null)
                .commit()
        }

        votadosAdapter = VotadosAdapter(votadosList) { position ->
            val otroFragmento = VotandoFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, otroFragmento)
                .addToBackStack(null)
                .commit()
        }

        rvVotar = view.findViewById(R.id.recyclerVotar)
        rvVotar.layoutManager = LinearLayoutManager(context)
        rvVotar.adapter = votadosAdapter
    }
}
