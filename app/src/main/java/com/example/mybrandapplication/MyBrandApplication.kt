package com.example.mybrandapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class MyBrandApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
                super.onActivityPreCreated(activity, savedInstanceState)
                val logMsgPrefix = "onActivityPreCreated (${activity.javaClass.simpleName}),"

                val brandColor = getBrandColorFromSharedPreferences(activity)
                Log.d(TAG, "$logMsgPrefix brandColor: ${brandColor?.toArgbHexString()}")

                val colorReplacementMap = brandColor?.let {
                    createColorReplacementMap(
                        R.color.runtime_brand_color,
                        it
                    )
                }
                Log.d(TAG, "$logMsgPrefix colorReplacementMap: $colorReplacementMap")

                val resourceLoaderAdded = colorReplacementMap?.let {
                    addResourcesLoaderToContext(activity, it)
                } ?: false
                Log.d(TAG, "$logMsgPrefix resourceLoaderAdded: $resourceLoaderAdded")

                val brandThemeOverlayEnabled = isBrandThemeOverlayEnabled(activity)
                Log.d(TAG, "$logMsgPrefix brandThemeOverlayEnabled: $brandThemeOverlayEnabled")

                if (resourceLoaderAdded && brandThemeOverlayEnabled) {
                    Log.d(TAG, "$logMsgPrefix BrandThemeOverlay applied")
                    activity.theme.applyStyle(R.style.BrandThemeOverlay, true)
                }
            }

            // No-ops
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {}
            override fun onActivityStarted(p0: Activity) {}
            override fun onActivityResumed(p0: Activity) {}
            override fun onActivityPaused(p0: Activity) {}
            override fun onActivityStopped(p0: Activity) {}
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
            override fun onActivityDestroyed(p0: Activity) {}
        })
    }

    companion object {
        private const val TAG = "MyBrandApplication"
    }
}