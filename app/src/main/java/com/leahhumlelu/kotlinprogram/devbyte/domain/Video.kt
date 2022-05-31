package com.leahhumlelu.kotlinprogram.devbyte.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val videoUrl:String,
    val title:String,
    val description:String,
    val thumbnail:String,
    val updated:String
    ):Parcelable
