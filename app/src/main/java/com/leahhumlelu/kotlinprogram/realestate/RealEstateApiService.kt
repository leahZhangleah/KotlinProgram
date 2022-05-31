package com.leahhumlelu.kotlinprogram.realestate

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://mars.udacity.com/"
private val retrofit:Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RealEstateApiService {
    @GET("realestate")
    suspend fun getRealEstateOverview(@Query("filter") filter:String): List<MarsProperty>
}

object RealEstateApi{
    val service:RealEstateApiService by lazy {
        retrofit.create(RealEstateApiService::class.java)
    }
}

enum class PropertyFilter(val type:String){
    SHOW_ALL(""),SHOW_BUY("buy"),SHOW_RENT("rent")
}

