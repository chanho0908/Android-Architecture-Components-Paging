package kr.co.lion.paginglv4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kr.co.lion.paginglv4.model.Items
import kr.co.lion.paginglv4.network.GitApi
import kr.co.lion.paginglv4.network.RetrofitInstance
import kr.co.lion.paginglv4.paging.GithubPagingSource

class MainViewModel(val str : String): ViewModel() {
    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<Items>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            GithubPagingSource(api, str) // 검색어 전달
        }
    )
        .flow
        .cachedIn(viewModelScope)
}