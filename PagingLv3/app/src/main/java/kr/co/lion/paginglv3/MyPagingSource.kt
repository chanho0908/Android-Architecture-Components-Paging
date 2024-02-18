package kr.co.lion.paginglv3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.co.lion.paginglv3.model.Data
import kr.co.lion.paginglv3.network.api.GithubApi
import kr.co.lion.paginglv3.network.api.PassengerApi
import kr.co.lion.paginglv3.newModel.NewItems

private const val STATING_KEY = 1
class MyPagingSource(private val githubApi : GithubApi): PagingSource<Int, NewItems>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewItems> {
        val page = params.key ?: STATING_KEY

        val response = githubApi.getData("android", page, params.loadSize)

        val data = response.items

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = page + (params.loadSize / 30)
        )
    }

    // Refresh(새로고침)
    // 리프레시 버튼을 누르면 현재 보고있는 아이템 페이지에서
    // 이 페이지의 데이터를 가져옴
    override fun getRefreshKey(state: PagingState<Int, NewItems>): Int? {
        Log.d("getRefreshKey", "start")

        // paging 라이브러리에서 제공하는 기능
        // 적당히 보고 있는 페이지 position 을 가져와서

        /**
         * Most recently accessed index in the list, including placeholders.
         *
         * `null` if no access in the [PagingData] has been made yet. E.g., if this snapshot was
         * generated before or during the first load.
         */

        val anchorPosition = state.anchorPosition

        // 적당히 보고 있는 페이지를 찾아서 데이터를 받아옴
        // 페이지를 return

        return anchorPosition?.let {
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}