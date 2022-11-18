package com.hermawan.compose.moviedb.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun changeDateFormat(data: String, oldFormat: String, newFormat: String): String {
    return try {
        val formatter = SimpleDateFormat(oldFormat, Locale.getDefault())
        val oldData = formatter.parse(data)
        SimpleDateFormat(newFormat, Locale.getDefault()).format(oldData!!)
    } catch (e: Exception) {
        "-"
    }
}