package com.example.mybrandapplication

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

fun addResourcesLoaderToContext(
    context: Context, colorReplacementMap: Map<Int, Int>
): Boolean {
    val resourcesLoader =
        ColorResourcesLoaderCreator.create(context, colorReplacementMap)
    return resourcesLoader?.let {
        context.resources.addLoaders(it)
        true
    } ?: false
}

fun createColorReplacementMap(
    @ColorRes runtimeBrandColor: Int,
    @ColorInt brandColor: Int
): Map<Int, Int> {
    // Overriding the runTimeBrandColor to point to brandColor when resources are resolved.
    // TODO: Better understand what material-components-android is doing.
    val colorReplacementMap = mutableMapOf<Int, Int>()
    colorReplacementMap[runtimeBrandColor] = brandColor
    return colorReplacementMap
}