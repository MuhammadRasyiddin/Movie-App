package dev.falcer.movieapp.data.entity

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    var page : Int = 1,
    @SerializedName("total_results")
    var totalResults : Int = 0,
    @SerializedName("total_pages")
    val totalPages : Int = 0,
    @SerializedName("results")
    var results : List<Movie> = listOf()
)