package corp.bcapc.top100pan

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.content.Context
import corp.bcapc.top100pan.model.Rank

class TwitchDataSourceFactory(val networkState: MutableLiveData<NetworkState>, val context: Context)
    : DataSource.Factory<Int, Rank>() {

    override fun create(): DataSource<Int, Rank> {
        return TwitchDataSource(TwitchRepository(context), networkState)
    }

}