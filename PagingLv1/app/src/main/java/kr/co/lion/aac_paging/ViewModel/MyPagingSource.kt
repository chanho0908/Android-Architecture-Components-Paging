package kr.co.lion.aac_paging.ViewModel

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kr.co.lion.aac_paging.model.User

private const val STARTING_KEY = 1

// 데이터 소스를 식별하기 위한 Class
// PagingSource<Key, Value>
class MyPagingSource: PagingSource<Int, User>() {

    init {
        Log.d("MyPagingSource", "init")
    }

    // 페이징이 실행되면 어떻게 할 것인지 정하는 부분
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("MyPagingSource", "load")
        Log.d("params.loadSize", params.loadSize.toString())
        Log.d("params.key", params.key.toString())

        // 페이징이 시작될 위치
        // 지정해주지 않으면 가장 처음부터 실행
        val page = params.key ?: STARTING_KEY
        Log.d("page", page.toString())

        // viewModel에서 지정해준 pageSize (30)
        // 1부터 시작해서 30 * 3
        val range = page.until(page + params.loadSize)
        Log.d("range", range.toString())

        // 처음 로드 되는게 아니면 3초 딜레이
        if (page != STARTING_KEY) delay(300)

        return LoadResult.Page(
            data = range.map { number->
                User(
                    id = number,
                    userName = "UserName is $number"
                )
            },

            // 위로 당겼을 때
            prevKey = null,
            // 아래로 당겼을 때
            nextKey = range.last +1
        )
    }

    // 새로 고침을 누르면 어떻게 할 것인지
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }



}