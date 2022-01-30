package com.casestudy.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDevice(
    val currency: String,
    val description: String,
    val price: Int,
    val title: String,
    val type: String,
    val id: String,
    val imageUrl: String,
    val isFavorite: Boolean
) : Parcelable