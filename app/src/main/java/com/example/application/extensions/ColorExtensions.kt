package com.example.application.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.application.R
import com.example.application.data.entity.Note

inline fun Note.Color.getColorInt(context: Context) = ContextCompat.getColor(context, getColorRes())

inline fun Note.Color.getColorRes() = when (this) {
    Note.Color.WHITE -> R.color.color_white
	Note.Color.VIOLET -> R.color.color_violet
    Note.Color.YELLOW -> R.color.color_yellow
    Note.Color.RED -> R.color.color_red
    Note.Color.GREEN -> R.color.color_green
}