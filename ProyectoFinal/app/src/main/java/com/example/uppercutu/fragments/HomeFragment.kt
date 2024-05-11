package com.example.uppercutu.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class HomeFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private var articles: List<Article> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchNews()

        val switchLanguage = view.findViewById<Switch>(R.id.switch1)
        switchLanguage.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.d("HomeFragment", "Switch activado - Cargando noticias en español")
                fetchNewsEsp()
            } else {
                Log.d("HomeFragment", "Switch desactivado - Cargando noticias en inglés")
                fetchNews()
            }
        }
    }



    private fun setupRecyclerView() {
        adapter = NewsAdapter { article ->
            // Aquí puedes manejar el clic en el artículo si es necesario
            // Por ejemplo, podrías abrir una actividad o fragmento de detalle del artículo
        }
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerHeadlines)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
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

    }

}
