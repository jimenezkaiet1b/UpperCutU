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

class CustomCartaPuntuaje : Fragment() {

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
        val saveButton: Button = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val boxer1NameEditText: EditText = view.findViewById(R.id.boxer1_name)
            val boxer2NameEditText: EditText = view.findViewById(R.id.boxer2_name)
            val numberOfRoundsSpinner: Spinner = view.findViewById(R.id.number_of_rounds)

            val boxer1Name = boxer1NameEditText.text.toString()
            val boxer2Name = boxer2NameEditText.text.toString()
            val numberOfRounds = numberOfRoundsSpinner.selectedItem.toString().toInt()

            // Crear una instancia del fragmento com.example.uppercutu.fragments.VotarFragment y pasar los datos como argumentos
            val votarFragment = VotarFragment().apply {
                arguments = Bundle().apply {
                    putString("boxer1Name", boxer1Name)
                    putString("boxer2Name", boxer2Name)
                    putInt("numberOfRounds", numberOfRounds)
                }
            }

            // Reemplazar el fragmento actual por el fragmento com.example.uppercutu.fragments.VotarFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, votarFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
