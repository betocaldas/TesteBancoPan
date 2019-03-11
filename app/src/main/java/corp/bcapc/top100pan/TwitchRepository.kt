package corp.bcapc.top100pan

import android.content.Context
import corp.bcapc.top100pan.Rest.RetrofitServer
import corp.bcapc.top100pan.model.TwitchResponse

class TwitchRepository(val context: Context) {

    val service by lazy { RetrofitServer.createServer(context) }

    suspend fun GetTop100(offset: Int):TwitchResponse {
            val request = service.GetTopGames(offset = "$offset")
            try {
                return request.await()
            } catch (e: Exception) {
                e.printStackTrace()
                return TwitchResponse.TwitchFail("t1",0, "t2")
            }
    }

}