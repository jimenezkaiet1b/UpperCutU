package com.example.uppercutu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uppercutu.R
import com.example.uppercutu.modelo.news.Article

class NewsAdapter(private val onArticleClick: (Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsRowViewHolder>() {
    inner class NewsRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.articleTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.articleDescription)
        val sourceTextView: TextView = itemView.findViewById(R.id.articleSource)
        val dateTimeTextView: TextView = itemView.findViewById(R.id.articleDateTime)
        val articleImage : ImageView = itemView.findViewById(R.id.articleImage)

    }

    private var articles: List<Article> = emptyList()
    private var filteredArticles: List<Article> = articles


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_news, parent, false)
        return NewsRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsRowViewHolder, position: Int) {
        if (filteredArticles.isNotEmpty() && position < filteredArticles.size) {
            val article = filteredArticles[position]
            holder.titleTextView.text = article.title
            holder.descriptionTextView.text = article.description
            holder.sourceTextView.text = article.source?.name
            holder.dateTimeTextView.text = article.publishedAt

            article.urlToImage?.let { url ->
                Glide.with(holder.itemView.context)
                    .load(url)
                    .error(R.drawable.uppercutu)
                    .into(holder.articleImage)
            }

            holder.itemView.setOnClickListener { onArticleClick.invoke(article) }
        } else {
            val article = articles[position]
            holder.titleTextView.text = article.title
            holder.descriptionTextView.text = article.description
            holder.sourceTextView.text = article.source?.name
            holder.dateTimeTextView.text = article.publishedAt

            article.urlToImage?.let { url ->
                Glide.with(holder.itemView.context)
                    .load(url)
                    .error(R.drawable.uppercutu)
                    .into(holder.articleImage)
            }

            holder.itemView.setOnClickListener { onArticleClick.invoke(article) }
        }
    }


    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    fun filterArticles(query: String) {
        filteredArticles = if (query.isEmpty()) {
            articles
        } else {
            articles.filter { article ->
                article.title.contains(query, ignoreCase = true) ||
                        article.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }



}
