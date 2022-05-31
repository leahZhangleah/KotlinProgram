package com.leahhumlelu.kotlinprogram.devbyte

import com.leahhumlelu.kotlinprogram.devbyte.domain.Video
import com.leahhumlelu.kotlinprogram.devbyte.network.NetworkVideo
import com.leahhumlelu.kotlinprogram.devbyte.network.NetworkVideoContainer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import com.leahhumlelu.kotlinprogram.devbyte.network.NetworkVideoContainer
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val BASE_URL ="https://devbytes.udacity.com/"
private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()
private val jsonAdapter = moshi.adapter(NetworkVideoContainer::class.java)
private val retrofit:Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
interface DevByteApiService {
    @GET("devbytes.json")
    suspend fun getDevByteVideos():NetworkVideoContainer

}

object DevByteApi{
    val service:DevByteApiService by lazy {
        retrofit.create(DevByteApiService::class.java)
    }

}