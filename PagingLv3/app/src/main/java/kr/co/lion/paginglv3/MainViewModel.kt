package kr.co.lion.paginglv3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kr.co.lion.paginglv3.model.Data
import kr.co.lion.paginglv3.network.api.PassengerApi
import kr.co.lion.paginglv3.network.RetrofitInstance
import kr.co.lion.paginglv3.network.api.GithubApi
import kr.co.lion.paginglv3.newModel.NewItems

class MainViewModel: ViewModel() {
    private val api = RetrofitInstance.getInstance().create(GithubApi::class.java)

    val items : Flow<PagingData<NewItems>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}