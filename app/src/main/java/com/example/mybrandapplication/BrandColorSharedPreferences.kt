package com.example.mybrandapplication

import android.content.Context
import androidx.annotation.ColorInt

private const val SHARED_PREFERENCE_FILE_NAME = "BrandColorSharedPreference"
private const val IS_BRAND_COLOR_EXPLICITLY_SET_KEY = "IS_BRAND_COLOR_EXPLICITLY_SET"
private const val BRAND_COLOR_KEY = "BRAND_COLOR"

fun storeBrandColorInSharedPreferences(
    context: Context,
    @ColorInt brandColor: Int
) {
    val preferences =
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    val preferencesEditor = preferences.edit()
    preferencesEditor.putBoolean(IS_BRAND_COLOR_EXPLICITLY_SET_KEY, true)
    preferencesEditor.putInt(BRAND_COLOR_KEY, brandColor)
    preferencesEditor.commit()
}

@ColorInt
fun fetchBrandColorFromSharedPreferences(context: Context): Int? {
    val preferences =
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    val isBrandColorExplicitlySet = preferences.getBoolean(IS_BRAND_COLOR_EXPLICITLY_SET_KEY, false)
    val brandColorInt = preferences.getInt(BRAND_COLOR_KEY, 0)
    return if (isBrandColorExplicitlySet) brandColorInt else null
}