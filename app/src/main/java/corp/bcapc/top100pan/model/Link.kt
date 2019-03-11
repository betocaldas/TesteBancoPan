package corp.bcapc.top100pan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(val self: String, val next: String ): Parcelable