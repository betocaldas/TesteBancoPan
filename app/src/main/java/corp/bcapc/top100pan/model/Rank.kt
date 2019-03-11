package corp.bcapc.top100pan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rank(val game: Game, val viewers: Int, val channels: Int): Parcelable{
    companion object {
        const val TAG = "Rank"
    }
}