package com.example.mybrandapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class MyBrandApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
                super.onActivityPreCreated(activity, savedInstanceState)
                val brandColor = fetchBrandColorFromSharedPreferences(activity)
                Log.d(TAG, "onActivityPreCreated (${activity.javaClass.simpleName}), brandColor: ${brandColor?.toArgbHexString()}")
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