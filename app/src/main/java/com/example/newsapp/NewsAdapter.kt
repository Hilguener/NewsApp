package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val articles: MutableList<Article>,
    private val onItemClickListener: (Int) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun updateArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener(adapterPosition)

                val articleUrl = articles[adapterPosition].url
                // Crie uma Intent para abrir o navegador com o URL da notícia
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
                context.startActivity(intent)
            }
        }

        fun bind(article: Article) {
            binding.apply {
                newsTitle.text = article.title
                newsDesc.text = article.description

                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.color.black)
                    .into(newsCover)
            }
        }
    }
}
