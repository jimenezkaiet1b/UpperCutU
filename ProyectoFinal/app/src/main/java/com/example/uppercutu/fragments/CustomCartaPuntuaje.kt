package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votados

class CustomCartaPuntuaje : Fragment() {

    private lateinit var onSaveClickListener: OnSaveClickListener

    private lateinit var votadosList: MutableList<Votados>
    private lateinit var votadosAdapter: VotadosAdapter

    fun setAdapterAndList(adapter: VotadosAdapter, list: MutableList<Votados>) {
        votadosAdapter = adapter
        votadosList = list
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_carta_puntuaje, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val boxer1Name: EditText = view.findViewById(R.id.boxer1_name)
        val boxer2Name: EditText = view.findViewById(R.id.boxer2_name)
        val rounds: Spinner = view.findViewById(R.id.number_of_rounds)

        val saveButton: Button = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            val boxer1NameText = boxer1Name.text.toString()
            val boxer2NameText = boxer2Name.text.toString()
            val rondas = rounds.selectedItem.toString().toInt()
            onSaveClickListener.onSaveClicked(boxer1NameText, boxer2NameText, rondas)
            val bundle = Bundle().apply {
                putString("boxer1Name", boxer1NameText)
                putString("boxer2Name", boxer2NameText)
            }
            val votandoFragment = VotandoFragment().apply {
                arguments = bundle
            }

            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, votandoFragment)
                .addToBackStack(null)
                .commit()


        }
    }

    fun setOnSaveClickListener(listener: OnSaveClickListener) {
        onSaveClickListener = listener
    }

    interface OnSaveClickListener {
        fun onSaveClicked(boxer1Name: String, boxer2Name: String, numberOfRounds: Int)
    }
}
