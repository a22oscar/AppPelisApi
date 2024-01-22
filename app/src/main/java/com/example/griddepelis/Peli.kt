package com.example.griddepelis

data class Peli(val imagen: Int, val texto: String)

data class TmdbResponse(
    val results: List<TmdbMovie>
)

data class TmdbMovie(
    val poster_path: String?,
    val title: String
)
