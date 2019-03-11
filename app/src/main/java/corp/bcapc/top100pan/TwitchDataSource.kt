package corp.bcapc.top100pan

import android.arch.paging.PageKeyedDataSource
import corp.bcapc.top100pan.Rest.RetrofitServer
import corp.bcapc.top100pan.model.Rank
import corp.bcapc.top100pan.model.TwitchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TwitchDataSource(private val twitchRepository: TwitchRepository)
    : PageKeyedDataSource<Int, Rank>(){
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Rank>) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = twitchRepository.GetTop100(0)
            if( response is TwitchResponse.TwitchSuccess) {
                callback.onResult(response.top, null, RetrofitServer.OFFSET)
            }else {

            }
            //TODO FAIL
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Rank>) {
        val next_offset = if (params.key == RetrofitServer.MAX_GAMES) {
            return
        } else {
            params.key + RetrofitServer.OFFSET
        }
        GlobalScope.launch(Dispatchers.Main) {
            val response = twitchRepository.GetTop100(next_offset)
            if( response is TwitchResponse.TwitchSuccess) {
                callback.onResult(response.top, next_offset)
            }
            //TODO FAIL
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Rank>) {
    }
}