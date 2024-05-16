package com.example.uppercutu.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.VotacionesAdapter
import com.example.uppercutu.data.Votacion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VotandoFragment : Fragment() {

    private lateinit var goBackButton: Button
    private lateinit var boxer1NameTextView: TextView
    private lateinit var boxer2NameTextView: TextView
    private lateinit var resulBox1TextView: TextView
    private lateinit var resulBox2TextView: TextView
    private lateinit var votacionesRecyclerView: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_votando, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        goBackButton = view.findViewById(R.id.goBack)
        boxer1NameTextView = view.findViewById(R.id.box1nombre)
        boxer2NameTextView = view.findViewById(R.id.box2nombre)
        resulBox1TextView = view.findViewById(R.id.resulBox1)
        resulBox2TextView = view.findViewById(R.id.resulBox2)
        votacionesRecyclerView = view.findViewById(R.id.votacionesRv)

        sharedPreferences = requireActivity().getSharedPreferences("votaciones", Context.MODE_PRIVATE)

        var rounds = 0

        arguments?.let {
            val boxer1Name = it.getString("boxer1Name")
            val boxer2Name = it.getString("boxer2Name")
            rounds = it.getInt("rounds")

            resulBox1TextView.text = it.getInt("resulBox1").toString()
            resulBox2TextView.text = it.getInt("resulBox2").toString()
            boxer1NameTextView.text = boxer1Name
            boxer2NameTextView.text = boxer2Name


        }

        val adapter = VotacionesAdapter(rounds) { totalButton1, totalButton2 ->
            resulBox1TextView.text = totalButton1.toString()
            resulBox2TextView.text = totalButton2.toString()


            // Guardar los nuevos valores en SharedPreferences
            sharedPreferences.edit().putInt("resulBox1", totalButton1).apply()
            sharedPreferences.edit().putInt("resulBox2", totalButton2).apply()
        }
        votacionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        votacionesRecyclerView.adapter = adapter

        goBackButton.setOnClickListener {
            //saveData()
            parentFragmentManager.popBackStack()
        }
    }


}
