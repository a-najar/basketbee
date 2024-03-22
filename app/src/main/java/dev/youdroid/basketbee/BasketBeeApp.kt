package dev.youdroid.basketbee

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.installations.ktx.installations
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BasketBeeApp : Application(), ImageLoaderFactory {


    override fun onCreate() {
        super.onCreate()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }


}