package com.leahhumlelu.kotlinprogram.realestate

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsProperty(
    val id:String,
    @SerializedName("img_src")
    val imgUrl:String,
    val type:String,
    val price:Double
):Parcelable
