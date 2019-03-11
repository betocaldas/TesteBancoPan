package corp.bcapc.top100pan

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import corp.bcapc.top100pan.Rest.RetrofitServer
import corp.bcapc.top100pan.model.Rank

class TwitchViewModel: ViewModel() {

    var liveData : LiveData<PagedList<Rank>>
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    init {
        liveData = createPagedList()
    }

    private fun createPagedList(): LiveData<PagedList<Rank>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(RetrofitServer.OFFSET)
            .setPageSize(RetrofitServer.OFFSET)
//            .setPrefetchDistance(1)
            .build()
        return LivePagedListBuilder(TwitchDataSourceFactory(networkState), config).setBoundaryCallback(
            object: PagedList.BoundaryCallback<Rank?>() {
            //TODO
        }).build()
    }
}