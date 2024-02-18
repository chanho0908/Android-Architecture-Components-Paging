package kr.co.lion.aac_paging.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kr.co.lion.aac_paging.model.User

class MainViewModel : ViewModel() {

    val items: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource()
        }
    ).flow
    .cachedIn(viewModelScope)
    // 데이터 스트림을 공유 가능하게 하며 제공된 CoroutineScope을 사용하여 로드된 데이터를 캐시

}