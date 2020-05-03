package dev.falcer.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import dev.falcer.movieapp.R
import dev.falcer.movieapp.utils.FakeDataConverter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val trendingAdapter = HomeTrendingAdapter()
    private val categoryAdapter = HomeCategoryAdapter()
    private val recentAdapter = HomeRecentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(R.layout.activity_main)
        setupTrending()
        setupCategory()
        setupRecent()
    }

    private fun setupTrending() {
        trendingAdapter.setData(FakeDataConverter.getMovieFromAsset(this.baseContext, "trending.json")!!)
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
        categoryAdapter.setData(FakeDataConverter.getGenreFromAsset(this.baseContext, "genres.json")!!.slice(0..3))
        rv_category.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 4)
        }
    }

    private fun setupRecent() {
        recentAdapter.setData(FakeDataConverter.getMovieFromAsset(this.baseContext, "now_playing.json")!!.slice(0..3))
        rv_recent.apply {
            adapter = recentAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }
}
