package kr.co.lion.paginglv2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kr.co.lion.paginglv2.model.GithubResponseItem
import kr.co.lion.paginglv2.network.GitApi
import kr.co.lion.paginglv2.network.RetrofitInstance
import kr.co.lion.paginglv2.paging.MyPagingSource

class MainViewModel : ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<GithubResponseItem>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)


}