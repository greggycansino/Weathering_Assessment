package com.accenture.weathering.data.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


// used to show a toast message in the UI Thread
fun Context.showToast(message: String) {
    Toast.makeText(this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show()
}

fun Activity.color(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
