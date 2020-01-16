package com.wispapp.gcpsoftwaretestapp.core.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.wispapp.gcpsoftwaretestapp.core.executors.AppExecutors
import java.io.InputStream
import java.net.URL


interface ImageLoader {

    fun loadImage(imageUrl: String, target: ImageView)
}

class ImageLoaderImpl(private val executors: AppExecutors) : ImageLoader {

    companion object {

        private const val LOG_TAG = "image_loader_logs"
    }

    override fun loadImage(imageUrl: String, target: ImageView) {
        executors.background.execute { loadUrl(imageUrl, target) }
    }

    private fun loadUrl(imageUrl: String, target: ImageView) {
        var bitmapImage: Bitmap? = null
        try {
            val inputStream: InputStream = URL(imageUrl).openStream()
            bitmapImage = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.message ?: "Error loading image")
            e.printStackTrace()
        }
        executors.mainThread.execute { target.setImageBitmap(bitmapImage) }
    }
}