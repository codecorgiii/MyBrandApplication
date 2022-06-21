package com.example.mybrandapplication

import android.content.Context
import androidx.annotation.ColorInt

private const val SHARED_PREFERENCE_FILE_NAME = "BrandColorSharedPreference"
private const val IS_BRAND_COLOR_EXPLICITLY_SET_KEY = "IS_BRAND_COLOR_EXPLICITLY_SET"
private const val BRAND_COLOR_KEY = "BRAND_COLOR"
private const val BRAND_THEME_OVERLAY_ENABLED_KEY = "BRAND_THEME_OVERLAY_ENABLED"

fun setBrandColorInSharedPreferences(
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
fun getBrandColorFromSharedPreferences(context: Context): Int? {
    val preferences =
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    val isBrandColorExplicitlySet = preferences.getBoolean(IS_BRAND_COLOR_EXPLICITLY_SET_KEY, false)
    val brandColorInt = preferences.getInt(BRAND_COLOR_KEY, 0)
    return if (isBrandColorExplicitlySet) brandColorInt else null
}

fun isBrandThemeOverlayEnabled(context: Context): Boolean {
    val preferences =
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    return preferences.getBoolean(BRAND_THEME_OVERLAY_ENABLED_KEY, false)
}

fun setBrandThemeOverlayEnabled(context: Context, enabled: Boolean) {
    val preferences =
        context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    val preferencesEditor = preferences.edit()
    preferencesEditor.putBoolean(BRAND_THEME_OVERLAY_ENABLED_KEY, enabled)
    preferencesEditor.commit()
}