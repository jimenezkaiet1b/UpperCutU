package com.example.uppercutu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotadosAdapter
import com.example.uppercutu.data.Votados
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class VotandoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_votando, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val boxer1Name = arguments?.getString("boxer1Name")
        val boxer2Name = arguments?.getString("boxer2Name")

        view.findViewById<TextView>(R.id.box1nombre).text = boxer1Name
        view.findViewById<TextView>(R.id.box2nombre).text = boxer2Name
    }

}