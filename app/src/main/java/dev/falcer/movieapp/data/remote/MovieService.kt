package dev.falcer.movieapp.data.remote

import dev.falcer.movieapp.data.entity.GenreList
import dev.falcer.movieapp.data.entity.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("genre/movie/list")
    fun getGenreMovieList(): Call<GenreList>

    @GET("discover/movie?with_genres=16&with_original_language=ja")
    fun getDiscoverMovie(@Query("sort_by") sortBy: String,
                        @Query("release_date.lte") releaseDateLte : String
    ): Call<MovieList>

    @GET("trending/movie/week")
    fun getTrendingMovie(): Call<MovieList>
}