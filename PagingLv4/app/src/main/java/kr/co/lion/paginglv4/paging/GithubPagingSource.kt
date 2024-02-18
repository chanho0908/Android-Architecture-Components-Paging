package kr.co.lion.paginglv4.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kr.co.lion.paginglv4.model.Items
import kr.co.lion.paginglv4.network.GitApi

private const val STARTING_KEY = 1

class GithubPagingSource(
    private val gitApi: GitApi,
    private val query: String
) : PagingSource<Int, Items>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {

        delay(1000)
        return try {
            val page = params.key ?: STARTING_KEY
            val response = gitApi.getData(query, page, params.loadSize)
            val data = response.items

            // 임시 에러 발생
            // 임시 에러 부분
            var count = 0

            if (page != 1) {
                count = (0..1).random()
            }

            Log.d("count", count.toString())

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + (params.loadSize / 30),
            )

            // 임시 에러 부분 끝

//            return LoadResult.Page(
//                data = data,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = page + (params.loadSize / 30)
//            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        val anchor = state.anchorPosition
        return anchor?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}