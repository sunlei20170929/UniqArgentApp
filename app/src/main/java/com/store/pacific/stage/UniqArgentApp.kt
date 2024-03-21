package com.store.pacific.stage

import android.app.Application
import androidx.work.Configuration
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class UniqArgentApp : Application(), Configuration.Provider, ImageLoaderFactory {


    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .build()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel( android.util.Log.DEBUG )
            .build()

}