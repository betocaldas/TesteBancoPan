package corp.bcapc.top100pan.Rest

import corp.bcapc.top100pan.model.TwitchResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchApi{

    @Headers("Client-ID: h9x6916itnu3xih5ywy736r6q0nmks")
    @GET("/kraken/games/top")
    fun GetTopGames(@Query("limit") limit: String = "${RetrofitServer.LIMIT_PER_REQUEST}"
                    , @Query("offset") offset: String)
            : Deferred<TwitchResponse.TwitchSuccess>
}