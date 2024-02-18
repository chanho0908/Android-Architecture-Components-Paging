package kr.co.lion.paginglv3.network.api

import kr.co.lion.paginglv3.newModel.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.github.com/search/repositories?q=android&page=1&per_page=90

interface GithubApi {

    @GET("search/repositories")
    suspend fun getData(
        @Query("q") query : String,
        @Query("page") page : Int,
        @Query("per_page") per_page : Int
    ) : GithubResponse

}