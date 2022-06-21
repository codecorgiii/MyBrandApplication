package com.example.mybrandapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import wang.relish.colorpicker.ColorPickerDialog

class MainActivity : AppCompatActivity() {
    @ColorInt
    private var currentColor = INITIAL_COLOR
    private lateinit var argbColorPreview: FrameLayout
    private lateinit var argbLabel: TextView
    private lateinit var argbAlphaComponentLabel: TextView
    private lateinit var argbRedComponentLabel: TextView
    private lateinit var argbGreenComponentLabel: TextView
    private lateinit var argbBlueComponentLabel: TextView
    private lateinit var recreateActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferencesBrandColor = fetchBrandColorFromSharedPreferences(this)
        if (sharedPreferencesBrandColor != null) {
            currentColor = sharedPreferencesBrandColor
        }

        argbColorPreview = findViewById(R.id.argb_color_preview)
        argbColorPreview.setBackgroundColor(currentColor)

        argbLabel = findViewById(R.id.argb_label)
        argbAlphaComponentLabel = findViewById(R.id.argb_alpha_component_label)
        argbRedComponentLabel = findViewById(R.id.argb_red_component_label)
        argbGreenComponentLabel = findViewById(R.id.argb_green_component_label)
        argbBlueComponentLabel = findViewById(R.id.argb_blue_component_label)
        updateArgbLabels(currentColor)

        recreateActivityButton = findViewById(R.id.recreate_activity_button)
        recreateActivityButton.setOnClickListener {
            this.recreate()
        }

        argbColorPreview.setOnClickListener {
            ColorPickerDialog.Builder(this, currentColor).setOnColorPickedListener { color ->
                currentColor = color
                updateArgbColorPreview(currentColor)
                updateArgbLabels(currentColor)
                storeBrandColorInSharedPreferences(this, currentColor)
            }.build().show()
        }
    }

    private fun updateArgbColorPreview(@ColorInt color: Int) {
        argbColorPreview.setBackgroundColor(color)
    }

    private fun updateArgbLabels(@ColorInt color: Int) {
        val alphaComponentHexString = String.format("%02X", color.alpha)
        val redComponentHexString = String.format("%02X", color.red)
        val greenComponentHexString = String.format("%02X", color.green)
        val blueComponentHexString = String.format("%02X", color.blue)
        val argbHexString = "#${color.toArgbHexString()}"

        argbLabel.text = argbHexString
        argbAlphaComponentLabel.text =
            resources.getString(R.string.rgba_alpha, alphaComponentHexString)
        argbRedComponentLabel.text = resources.getString(R.string.rgba_red, redComponentHexString)
        argbGreenComponentLabel.text =
            resources.getString(R.string.rgba_green, greenComponentHexString)
        argbBlueComponentLabel.text =
            resources.getString(R.string.rgba_blue, blueComponentHexString)
    }

    companion object {
        @ColorInt
        const val INITIAL_COLOR = Color.BLACK
    }
}

