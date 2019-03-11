package corp.bcapc.top100pan

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import corp.bcapc.top100pan.model.Rank

class TwitchDataSourceFactory(val networkState: MutableLiveData<NetworkState>): DataSource.Factory<Int, Rank>() {

    override fun create(): DataSource<Int, Rank> {
        return TwitchDataSource(TwitchRepository(networkState))
    }

}