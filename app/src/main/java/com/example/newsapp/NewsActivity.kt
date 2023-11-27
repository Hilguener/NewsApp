package com.example.newsapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var repository: NewsRepository
    private lateinit var binding: ActivityNewsBinding
    private lateinit var rv: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv = binding.rvMain
        progressBar = binding.progressBar

        adapter = NewsAdapter(mutableListOf(), onItemClickListener = { position ->
            // Ação ao clicar em um item do RecyclerView
        }, this)

        val wallStreetJournalApi = WallStreetJournalAPI()
        repository = NewsRepositoryImpl(wallStreetJournalApi)

        repository.getArticles(
            onSuccess = { articles ->
                runOnUiThread {
                    adapter.updateArticles(articles)
                    progressBar.visibility = View.GONE
                }
            },
            onError = { error ->
                runOnUiThread {
                    // Trate os erros
                    Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }
            }
        )

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
}

