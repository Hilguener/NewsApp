package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.datasource.NewsRemoteDataSource
import com.example.newsapp.model.Article
import com.example.newsapp.presentation.HomePresenter

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var rv: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.progressBar
        val dataSource = NewsRemoteDataSource()
        presenter = HomePresenter(this, dataSource)
        rv = binding.rvMain


        adapter = NewsAdapter(mutableListOf(), onItemClickListener = { position ->
            // Ação ao clicar em um item do RecyclerView
        }, this)


        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        presenter.findAllNews()
    }

    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

    fun showNews(response: List<Article>){
        adapter.updateArticles(response)
        adapter.notifyDataSetChanged()
    }
    fun onError(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

