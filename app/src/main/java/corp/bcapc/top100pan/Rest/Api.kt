package corp.bcapc.top100pan.Rest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import corp.bcapc.top100pan.model.TwitchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api{

    @Headers("Client-ID: h9x6916itnu3xih5ywy736r6q0nmks")
    @GET("/kraken/games/top")
    fun GetTopGames(@Query("limit") limit: String = "${RetrofitServer.LIMIT_PER_REQUEST}"
                    , @Query("offset") offset: String)
            : Deferred<TwitchResponse.TwitchSuccess>
}

object RetrofitServer {
    const val TWITCH_URL = "https://api.twitch.tv"
    const val LIMIT_PER_REQUEST = 10
    const val MAX_GAMES = 100
    const val OFFSET = 10

    fun createServer(): Api {
        return Retrofit.Builder()
            .baseUrl(TWITCH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(Api::class.java)
    }
}