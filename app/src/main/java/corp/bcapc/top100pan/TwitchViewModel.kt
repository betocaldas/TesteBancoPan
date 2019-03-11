package corp.bcapc.top100pan

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import corp.bcapc.top100pan.Rest.RetrofitServer
import corp.bcapc.top100pan.model.Rank

class TwitchViewModel(application: Application) : AndroidViewModel(application) {

    var liveData : LiveData<PagedList<Rank>>
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    init {
        liveData = createPagedList()
    }

    private fun createPagedList(): LiveData<PagedList<Rank>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(RetrofitServer.OFFSET)
            .build()
        return LivePagedListBuilder(TwitchDataSourceFactory(networkState, getApplication()), config).setBoundaryCallback(
            object: PagedList.BoundaryCallback<Rank?>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                }

                override fun onItemAtEndLoaded(itemAtEnd: Rank) {
                    super.onItemAtEndLoaded(itemAtEnd)
                }

                override fun onItemAtFrontLoaded(itemAtFront: Rank) {
                    super.onItemAtFrontLoaded(itemAtFront)
                }
            }).build()
    }
}