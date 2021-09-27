package com.example.myshops.data

import android.os.Parcelable
import androidx.activity.result.ActivityResultLauncher
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
@Entity(tableName = "my_purchases")
data class Purchases(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val purchaseName: String,
        val purchaseDesc: String,
        val purchaseCount: Int,
        val checkbox: Boolean

): Parcelable