package com.example.griddepelis

import PeliAdapter
import TmdbApiService
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griddepelis.R
import com.example.griddepelis.TmdbResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var peliAdapter: PeliAdapter

    private val tmdbApiService = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TmdbApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        peliAdapter = PeliAdapter(emptyList())
        recyclerView.adapter = peliAdapter

        // Llama a la función para cargar las películas
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        val apiKey = "994bb2c84ee0c5e27a72cdc3f102d12c"

        val call = tmdbApiService.getPopularMovies(apiKey, "en-US", 1)
        call.enqueue(object : Callback<TmdbResponse> {
            override fun onResponse(call: Call<TmdbResponse>, response: Response<TmdbResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()

                    // Actualiza el adaptador en el hilo principal
                    runOnUiThread {
                        peliAdapter.setData(movies)
                    }
                }
            }

            override fun onFailure(call: Call<TmdbResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}


