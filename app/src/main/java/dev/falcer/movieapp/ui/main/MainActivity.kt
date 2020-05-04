package dev.falcer.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.appbar.AppBarLayout
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Genre
import dev.falcer.movieapp.data.entity.GenreList
import dev.falcer.movieapp.data.entity.MovieList
import dev.falcer.movieapp.data.remote.MovieRetrofitClient
import dev.falcer.movieapp.data.remote.MovieService
import dev.falcer.movieapp.utils.FakeDataConverter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rv_category
import kotlinx.android.synthetic.main.activity_main.rv_recent
import kotlinx.android.synthetic.main.activity_main.vp_trending
import kotlinx.android.synthetic.main.activity_main_custom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val trendingAdapter = HomeTrendingAdapter()
    private val categoryAdapter = HomeCategoryAdapter()
    private val recentAdapter = HomeRecentAdapter()
    private val mainAdapter = MainAdapter(mutableListOf())
    private val movieClient : MovieService = MovieRetrofitClient.getService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(R.layout.activity_main_custom)
        setSupportActionBar(toolbar)
        rv_main.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2).apply {
                spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
                    override fun getSpanSize(position: Int): Int {
                        return if(position <= 1){
                            2
                        } else {
                           1
                        }
                    }
                }
            }
        }

        setupTrending()
        setupCategory()
        setupRecent()
    }

    private fun setupTrending() {
//        trendingAdapter.setData(FakeDataConverter.getMovieFromAsset(this.baseContext, "trending.json")!!)

        movieClient.getTrendingMovie().enqueue(object : Callback<MovieList>{
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                val movieList = response.body()
                val trending = movieList?.results
                trendingAdapter.addData(trending)
            }
        })
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(56))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.95f + r * 0.15f
        }
        vp_trending.apply {
            adapter = trendingAdapter
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
        }
        vp_trending.setPageTransformer(compositePageTransformer)
    }

    private fun setupCategory() {
        //mainAdapter.addCategory(FakeDataConverter.getGenreFromAsset(this.baseContext, "genres.json")!!.slice(0..3))
        movieClient.getGenreMovieList().enqueue(object : Callback<GenreList> {
            override fun onFailure(call: Call<GenreList>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<GenreList>, response: Response<GenreList>) {
                val  genreList = response.body()
                val genres = genreList?.genres
                mainAdapter.addCategory(genres)

            }

        })
    }

    private fun setupRecent() {
        //mainAdapter.addRecent(FakeDataConverter.getMovieFromAsset(this.baseContext, "now_playing.json")!!.slice(0..3))
        val format = SimpleDateFormat("yyyy-MM-dd")
        val curentDate : String = format.format(Date())
        movieClient.getDiscoverMovie("release_date.desc", curentDate.toString()).enqueue(object : Callback<MovieList>{
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                val  movieList = response.body()
                val recent = movieList?.results
                mainAdapter.addRecent(recent)
            }

        })
    }
}
