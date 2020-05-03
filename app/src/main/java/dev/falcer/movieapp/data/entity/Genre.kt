package dev.falcer.movieapp.data.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = ""
)