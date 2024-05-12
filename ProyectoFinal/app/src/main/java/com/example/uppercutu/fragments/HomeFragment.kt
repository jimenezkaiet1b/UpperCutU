package com.example.uppercutu.fragments

import android.app.AlertDialog
import com.example.uppercutu.adapters.EventosAdapter
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.adapters.NewsAdapter
import com.example.uppercutu.api.NewsApi
import com.example.uppercutu.api.RetrofitInstance
import com.example.uppercutu.api.SportApi
import com.example.uppercutu.modelo.news.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar


class HomeFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var adapterEventos: EventosAdapter
    private var articles: List<Article> = emptyList()
    private lateinit var date :String
    private var empresa : String = "UFC"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchFilterMenu_searchNoticias -> {

                fetchNews()
                true
            }
            R.id.searchFilterMenu_searchEventos -> {

                fetchEvents()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date = "2024"
        setupRecyclerView()
        fetchNews()
        fetchEvents()

        val switchLanguage = view.findViewById<Switch>(R.id.switch1)
        switchLanguage.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //Log.d("HomeFragment", "Switch activado - Cargando noticias en español")
                fetchNewsEsp()
            } else {
                //Log.d("HomeFragment", "Switch desactivado - Cargando noticias en inglés")
                fetchNews()
            }
        }

        val datePickerButton = view.findViewById<Button>(R.id.datePickerButton)
        datePickerButton.setOnClickListener {
            showYearPickerDialog()
        }

        val searchView = view.findViewById<SearchView>(R.id.home_searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterArticles(newText.orEmpty())
                return true
            }
        })

    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter { article ->
            val noticiaFragment = NoticiaFragment()
            noticiaFragment.setArticle(article)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, noticiaFragment)
                .addToBackStack(null)
                .commit()
        }
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerHeadlines)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter

        adapterEventos = EventosAdapter(requireContext(), emptyList())
        val  recyclerViewEventos = view?.findViewById<RecyclerView>(R.id.rvHorarios)
        recyclerViewEventos?.layoutManager = LinearLayoutManager(context)
        recyclerViewEventos?.adapter = adapterEventos
    }

    private fun fetchNews() {
        val api = RetrofitInstance.retrofit.create(NewsApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val response = api.boxingNews()
            if (response.isSuccessful) {
                val boxingNews = response.body()
                articles = boxingNews?.articles ?: emptyList()
                adapter.updateArticles(articles)
            } else {
                Toast.makeText(context, "Error fetching news", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchNewsEsp() {
        val api = RetrofitInstance.retrofit.create(NewsApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val response = api.boxingNewsEsp()
            if (response.isSuccessful) {
                val boxingNews = response.body()
                articles = boxingNews?.articles ?: emptyList()
                adapter.updateArticles(articles)
            } else {
                Toast.makeText(context, "Error fetching news", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchEvents() {
        val sportApi = SportApi()
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                sportApi.getSchedule(empresa, date)
            }
            if (response.isNotEmpty()) {
                // Parsear los eventos de la respuesta
                Log.d("Response", response.toString())
                val eventos = sportApi.parseSchedule(response)
                // Actualizar el RecyclerView con los eventos
                adapterEventos.updateEvents(eventos)
            } else {
                Toast.makeText(context, "Error fetching events", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showYearPickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)

        val yearPicker = NumberPicker(requireContext())
        yearPicker.minValue = 1980
        yearPicker.maxValue = currentYear
        yearPicker.value = currentYear

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Seleccionar Año")
            .setView(yearPicker)
            .setPositiveButton("Seleccionar") { _, _ ->
                val selectedYear = yearPicker.value
                // Manejar el año seleccionado
                date = selectedYear.toString()
                fetchEvents()
                Toast.makeText(context, "Año seleccionado: $selectedYear", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }





}
