package dev.falcer.movieapp.data.remote

import dev.falcer.movieapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitClient {
    @Volatile
    var INSTANCE: MovieService? = null

    private  val interceptor = Interceptor{
        val url : HttpUrl = it.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request : Request = it.request().newBuilder().url(url)
            .build()

        it.proceed(request)
    }

    private  val client: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .build()

    fun getService() : MovieService{
        return INSTANCE ?: synchronized(this){
            val instance : MovieService = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
            INSTANCE = instance
            instance
        }
    }
}