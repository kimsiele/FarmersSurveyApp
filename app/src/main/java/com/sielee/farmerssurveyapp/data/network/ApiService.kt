package com.sielee.farmerssurveyapp.data.network

import com.sielee.farmerssurveyapp.data.models.Survey
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("d628facc-ec18-431d-a8fc-9c096e00709a")
    suspend fun getSurvey():Survey
}
const val BASE_URL:String = "https://run.mocky.io/v3/"
val logger = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}
val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
object SurveyApi{
    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
