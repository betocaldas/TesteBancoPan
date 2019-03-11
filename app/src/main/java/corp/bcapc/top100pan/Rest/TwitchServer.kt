package corp.bcapc.top100pan.Rest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {
    const val TWITCH_URL = "https://api.twitch.tv"
    const val LIMIT_PER_REQUEST = 10
    const val MAX_GAMES = 100
    const val OFFSET = 10

    val cacheSize = (5 * 1024 * 1024).toLong()

    fun createServer(context: Context): TwitchApi {
        val cache = Cache(context.cacheDir, cacheSize)
        return Retrofit.Builder()
            .baseUrl(TWITCH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient(context, cache))
            .build()
            .create(TwitchApi::class.java)
    }

    fun isConnected(context: Context): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun okHttpClient(context: Context, cache: Cache) = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()

            request = if (isConnected(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60).build()
            chain.proceed(request)
        }
        .build()
}