package com.example.myshops.data.jointpurchases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JointPurchases(
    var name: String= "",
    var desc: String= "",
    var count: Int= 0,
    var chekbox: Boolean = false
): Parcelable
