package kr.co.lion.paginglv3.network.api

import kr.co.lion.paginglv3.model.PassengerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PassengerApi {

    // https://api.instantwebtools.net/v1/passenger?page=1&size=90

    // https://api.instantwebtools.net/v1/passenger?page=4&size=30
    // https://api.instantwebtools.net/v1/passenger?page=5&size=30

    @GET("passenger")
    suspend fun getData(
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : Response<PassengerResponse>

}