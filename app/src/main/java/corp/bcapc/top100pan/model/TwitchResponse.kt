package corp.bcapc.top100pan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val TAG = "TwitchResponse"
sealed class TwitchResponse {
    @Parcelize
    data class TwitchSuccess(val _total: Int, val _links: Link, val top: List<Rank>): Parcelable, TwitchResponse()

    @Parcelize
    data class TwitchFail(val error: String, val status: Int, val message: String): Parcelable, TwitchResponse()
}
