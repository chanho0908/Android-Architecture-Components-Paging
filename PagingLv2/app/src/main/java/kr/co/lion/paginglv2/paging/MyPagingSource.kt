package kr.co.lion.paginglv2.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kr.co.lion.paginglv2.model.GithubResponseItem
import kr.co.lion.paginglv2.network.GitApi
private const val STARTING_KEY = 1

class MyPagingSource(
    private val githubService : GitApi
) : PagingSource<Int, GithubResponseItem>() {

    init {
        Log.d("MyPagingSource", "init")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponseItem> {

        Log.d("MyPagingSource", "------------------------------")
        Log.d("MyPagingSource", "------------------START------------")
        Log.d("MyPagingSource", "------------------------------")

        Log.d("MyPagingSource", "load")
        Log.d("MyPagingSource", "params.key :" + params.key)

        val page = params.key ?: STARTING_KEY

        Log.d("MyPagingSource", "page : $page")

        val response = githubService.getData(page, params.loadSize)

        Log.d("MyPagingSource", "page : $response")
        Log.d("MyPagingSource", response.body().toString())

        val data = response.body()

        Log.d("data", data.toString())

        if(page != 1) {
            delay(3000)
        }

        Log.d("params.loadSize", params.loadSize.toString())
        Log.d("params.loadSize", (params.loadSize / 30).toString())

        if(data != null) {

            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = page + (params.loadSize / 30)
            )

        } else {

            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )

        }



        // 30개씩 데이터를 가져오고 싶습니다.

        // https://api.github.com/users/google/repos?page=1&per_page=30
        // https://api.github.com/users/google/repos?page=2&per_page=30
        // https://api.github.com/users/google/repos?page=3&per_page=30
        // https://api.github.com/users/google/repos?page=4&per_page=30

        // Paging

        // https://api.github.com/users/google/repos?page=1&per_page=90

        // page = 2 page = 3 필요없음

        // https://api.github.com/users/google/repos?page=4&per_page=30


        // 1 4 5 6 7 8 9 10
        // 1 + 90/30 = 4
        // 4 + 30/30 = 4 + 1 = 5
        // 5 + 30/30 = 6

    }

    override fun getRefreshKey(state: PagingState<Int, GithubResponseItem>): Int? {
        return null
    }

}