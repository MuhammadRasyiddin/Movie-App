package dev.falcer.movieapp.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi.Builder
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.falcer.movieapp.data.entity.Genre
import dev.falcer.movieapp.data.entity.Movie

object FakeDataConverter {

//    private val moshi = Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

    private val gson = Gson()

    fun getMovieFromAsset(context: Context, file: String): List<Movie>? {

//        val listType = Types.newParameterizedType(List::class.java, Movie::class.java)
//        val adapter: JsonAdapter<List<Movie>> = moshi.adapter(listType)


        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        return gson.fromJson(myjson, Array<Movie>::class.java).toList()
    }

    fun getGenreFromAsset(context: Context, file: String): List<Genre>? {
//
//        val listType = Types.newParameterizedType(List::class.java, Genre::class.java)
//        val adapter: JsonAdapter<List<Genre>> = moshi.adapter(listType)
//

        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        return gson.fromJson(myjson, Array<Genre>::class.java).toList()
    }

}