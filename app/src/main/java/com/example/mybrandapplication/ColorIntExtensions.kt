package com.example.mybrandapplication

fun Int.toArgbHexString(): String =
    String.format("%02X%02X%02X%02X", this.alpha, this.red, this.green, this.blue)

inline val Int.alpha: Int
    get() = (this shr 24) and 0xff

inline val Int.red: Int
    get() = (this shr 16) and 0xff

inline val Int.green: Int
    get() = (this shr 8) and 0xff

inline val Int.blue: Int
    get() = this and 0xff