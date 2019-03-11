package corp.bcapc.top100pan

import android.arch.lifecycle.MutableLiveData
import corp.bcapc.top100pan.Rest.RetrofitServer
import corp.bcapc.top100pan.model.TwitchResponse

class TwitchRepository(val networkState: MutableLiveData<NetworkState>) {

//    var currentPosition = 0
    val service = RetrofitServer.createServer()

    suspend fun GetTop100(offset: Int):TwitchResponse {
//        GlobalScope.launch(Dispatchers.Main) {
//            val request = service.GetTopGames(offset = "$currentPosition")
            val request = service.GetTopGames(offset = "$offset")
            try {
                return request.await()
            } catch (e: Exception) {
                e.printStackTrace()
                return TwitchResponse.TwitchFail("t1",0, "t2")
            }
//        }
    }

//    fun GetTop100(liveData: MutableLiveData<TwitchResponse>) {
//        GlobalScope.launch(Dispatchers.Main) {
//            val request = service.GetTopGames(offset = "$currentPosition")
//            try {
//                val response = request.await()
//                currentPosition += RetrofitServer.OFFSET
//                liveData.value = response
//            } catch (e: Exception) {
//                e.printStackTrace()
//                liveData.value = TwitchResponse.TwitchFail("t1",0, "t2")
//            }
//        }
//    }
}