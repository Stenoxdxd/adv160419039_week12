package com.ubaya.advweek4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.advweek4.R
import java.lang.Exception

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view:ImageView, url:String, pb:ProgressBar){
    view.LoadImage(url,pb)
}

fun ImageView.LoadImage(url: String, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess(){
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?){

            }
        })
}