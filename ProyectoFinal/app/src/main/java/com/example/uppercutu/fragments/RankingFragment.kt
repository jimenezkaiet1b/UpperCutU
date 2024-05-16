package com.example.uppercutu.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.FightersAdapter
import com.example.uppercutu.api.JsonReader
import com.example.uppercutu.api.RetrofitInstance
import com.example.uppercutu.api.SearchResponse
import com.example.uppercutu.modelo.fighters.Fighter
import com.example.uppercutu.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fightersAdapter: FightersAdapter
    private lateinit var jsonReader: JsonReader
    private lateinit var allFighters: List<Fighter>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setup(requireContext())
        setupButtons(view)
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rvPesos)
        fightersAdapter = FightersAdapter(emptyList())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = fightersAdapter
    }

    private fun setup(context: Context) {
        jsonReader = JsonReader(context)
        val spinnerFecha = view?.findViewById<Spinner>(R.id.spinnerFecha)
        spinnerFecha?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDate = parent.getItemAtPosition(position).toString()
                updateDataForDate(selectedDate, context)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
        // Cargar datos iniciales
        val initialDate = spinnerFecha?.selectedItem?.toString() ?: "2019-01-01"
        updateDataForDate(initialDate, context)
    }

    private fun updateDataForDate(targetDate: String, context: Context) {
        val jsonData = when (targetDate) {
            "2019-01-01" -> jsonReader.loadJsonFromFile(context, "filteredData_2019.json")
            "2020-01-20" -> jsonReader.loadJsonFromFile(context, "filteredData_2020.json")
            "2021-01-18" -> jsonReader.loadJsonFromFile(context, "filteredData_2021.json")
            "2022-01-17" -> jsonReader.loadJsonFromFile(context, "filteredData_2022.json")
            "2023-01-17" -> jsonReader.loadJsonFromFile(context, "filteredData_2023.json")
            "2024-01-16" -> jsonReader.loadJsonFromFile(context, "filteredData_2024.json")
            else -> jsonReader.loadJsonFromFile(context, "filteredData_2019.json")
        }
        Log.d("RankingFragment", "aioi: $jsonData")
        jsonData?.let {
            val filteredData = jsonReader.filterDataByDate(it, targetDate)
            filteredData?.let { filteredArray ->
                val fighters = mutableListOf<Fighter>()
                for (i in 0 until filteredArray.length()) {
                    val fighterJsonObject = filteredArray.getJSONObject(i)
                    val fighterName = fighterJsonObject.getString("fighter")
                    val fighterRank = fighterJsonObject.getString("rank")
                    val fighterWeightClass = fighterJsonObject.getString("weightclass")
                    val fighterDate = fighterJsonObject.getString("date")
                    fighters.add(Fighter(fighterName, fighterRank, fighterWeightClass, fighterDate, ""))
                }
                allFighters = fighters
                fightersAdapter.updateFighters(fighters)
                updateFightersWithImages(fighters) // Llamada para actualizar imágenes
            }
        } ?: Log.e("RankingFragment", "Error loading JSON data for date: $targetDate")
    }

    private fun setupButtons(view: View) {
        view.findViewById<Button>(R.id.btnPP)?.setOnClickListener {
            filterFightersByWeightClass("Pound-for-Pound")
        }
        view.findViewById<Button>(R.id.btnfly)?.setOnClickListener {
            filterFightersByWeightClass("Flyweight")
        }
        view.findViewById<Button>(R.id.btnbantam)?.setOnClickListener {
            filterFightersByWeightClass("Bantamweight")
        }
        view.findViewById<Button>(R.id.feather)?.setOnClickListener {
            filterFightersByWeightClass("Featherweight")
        }
        view.findViewById<Button>(R.id.ligthw)?.setOnClickListener {
            filterFightersByWeightClass("Lightweight")
        }
        view.findViewById<Button>(R.id.welter)?.setOnClickListener {
            filterFightersByWeightClass("Welterweight")
        }
        view.findViewById<Button>(R.id.middle)?.setOnClickListener {
            filterFightersByWeightClass("Middleweight")
        }
        view.findViewById<Button>(R.id.lightHeavy)?.setOnClickListener {
            filterFightersByWeightClass("Light Heavyweight")
        }
        view.findViewById<Button>(R.id.heavy)?.setOnClickListener {
            filterFightersByWeightClass("Heavyweight")
        }
    }

    private fun filterFightersByWeightClass(weightClass: String) {
        val filteredFighters = allFighters.filter { it.weightClass == weightClass }
        fightersAdapter.updateFighters(filteredFighters)
        updateFightersWithImages(filteredFighters) // Llamada para actualizar imágenes
    }

    private fun updateFightersWithImages(fighters: List<Fighter>) {
        for (fighter in fighters) {
            fighter.name?.let { name ->
                RetrofitInstance.RetrofitInstance.api.searchImages(
                    Constants.API_KEYIMAGES,
                    Constants.CX_ID,
                    name
                ).enqueue(object : Callback<SearchResponse> {
                    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                        if (response.isSuccessful) {
                            val imageUrl = response.body()?.items?.firstOrNull()?.link ?: ""
                            fighter.imageUrl = imageUrl
                            fightersAdapter.updateFighters(fighters)
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        Log.e("RankingFragment", "Error fetching image: ${t.message}")
                    }
                })
            }
        }
    }
}
