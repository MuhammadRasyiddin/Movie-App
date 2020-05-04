package dev.falcer.movieapp.data.entity

import com.google.gson.annotations.SerializedName

data class GenreList(
    @SerializedName("genres")
    var genres : List<Genre> = listOf()
)