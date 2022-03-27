package com.example.simpleweather.view

import com.example.simpleweather.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val DAY_FULL_MONTH_NAME = "dd MMMM"
const val DAY_WEEK_NAME_LONG = "dd EEEE"
const val HOUR_DOUBLE_DOT_MINUTE = "HH:mm"

fun Long.toDataFormatOf(format : String) : String {
    val cal = Calendar.getInstance()
    val timeZone = cal.timeZone
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = timeZone
    return sdf.format(Date(this * 1000))
}

fun Double.toDegree() = (this * 273.15).roundToInt().toString()

fun Double.toPercentString(extraPart : String = "") = (this * 100).roundToInt().toString() + extraPart

fun String.provideIcon() = when(this){
    "01n", "01d" -> R.drawable.ic_sun
    "02n", "02d" -> R.drawable.ic_sun
    "03n", "03d" -> R.drawable.ic_sun
    "04n", "04d" -> R.drawable.ic_sun
    "09n", "09d" -> R.drawable.ic_sun
    "10n", "10d" -> R.drawable.ic_sun
    "11n", "11d" -> R.drawable.ic_sun
    "13n", "13d" -> R.drawable.ic_sun
    "50n", "50d" -> R.drawable.ic_sun
    else -> R.drawable.ic_error_24
}

fun String.provideIconSun() = when(this){
    "01n", "01d" -> R.mipmap.palm_sun_3x
    "02n", "02d" -> R.mipmap.palm_sun_3x
    "03n", "03d" -> R.mipmap.palm_sun_3x
    "04n", "04d" -> R.mipmap.palm_sun_3x
    "09n", "09d" -> R.mipmap.palm_sun_3x
    "10n", "10d" -> R.mipmap.palm_sun_3x
    "11n", "11d" -> R.mipmap.palm_sun_3x
    "13n", "13d" -> R.mipmap.palm_sun_3x
    "50n", "50d" -> R.mipmap.palm_sun_3x
    else -> R.drawable.ic_error_24
}