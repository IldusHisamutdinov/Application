package com.example.application.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: String,
    var title: String,
    var text: String,
    val color: Color = Color.WHITE,
    val lastChanged: Date = Date()
): Parcelable {

    constructor(): this(
        UUID.randomUUID().toString(),
        "Моя первая заметка",
        "Kotlin очень краткий, но при этом выразительный язык"
    ) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    enum class Color {
        WHITE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
    }
}


