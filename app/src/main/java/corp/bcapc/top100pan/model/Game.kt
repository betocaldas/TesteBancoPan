package corp.bcapc.top100pan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(val name: String, val box: Box): Parcelable