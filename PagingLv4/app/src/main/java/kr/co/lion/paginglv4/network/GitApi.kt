package kr.co.lion.paginglv4.network

import kr.co.lion.paginglv4.model.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    // https://api.github.com/search/repositories?q=android&page=1&per_page=90

    @GET("search/repositories")
    suspend fun getData(
        @Query("q") query : String,
        @Query("page") page : Int,
        @Query("per_page") per_page : Int,
    ) : GithubResponse

}