package com.example.uppercutu.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.uppercutu.R
import com.example.uppercutu.modelo.news.Article

class NoticiaFragment : Fragment() {
    private lateinit var article: Article
    private lateinit var goBackNoticia: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_noticia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)

    }

    private fun setup(view: View){
        // Mostrar la información del artículo en los elementos de diseño
        view.findViewById<TextView>(R.id.tituloNoticia).text = article.title
        view.findViewById<TextView>(R.id.descripcionNoticia).text = article.content
        view.findViewById<TextView>(R.id.autorNoticia).text = article.author
        view.findViewById<TextView>(R.id.fechaNoticia).text = article.publishedAt


        val urlTextView = view.findViewById<TextView>(R.id.urlNoticia)
        urlTextView.setOnClickListener {
            // Abrir la URL en un navegador web
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(intent)
        }

        // Cargar la imagen del artículo usando Glide
        Glide.with(this)
            .load(article.urlToImage)
            .error(R.drawable.uppercutu) // Imagen de error por si no se puede cargar la imagen
            .into(view.findViewById<ImageView>(R.id.imagenNoticia))

        goBackNoticia = view.findViewById(R.id.goBackNoticia)
        goBackNoticia.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }


    fun setArticle(article: Article) {
        this.article = article
    }
}