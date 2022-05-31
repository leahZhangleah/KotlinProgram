package com.leahhumlelu.kotlinprogram.realestate

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leahhumlelu.kotlinprogram.R

@BindingAdapter("imageSrcUrl")
fun ImageBindingAdapter(imgView:ImageView,imgSrcUrl:String?) {
    imgSrcUrl?.let {
        val imgUri = imgSrcUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image_24))
            .into(imgView)

    }
}

@BindingAdapter("propertyList")
fun propertiesBindingAdapter(rv:RecyclerView,list:List<MarsProperty>?){
    list?.let {
        val adapter = rv.adapter as PropertyAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("loadingStatus")
fun statusBindingAdapter(iv:ImageView,status:Status){
    when(status){
        Status.LOADING-> {
            iv.visibility=View.VISIBLE
            iv.setImageResource(R.drawable.loading_animation)}
        Status.FAILURE->{
            iv.visibility=View.VISIBLE
            iv.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        Status.SUCCESS->
        iv.visibility= View.INVISIBLE
    }
}