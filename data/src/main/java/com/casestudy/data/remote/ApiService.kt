package com.casestudy.data.remote
import com.casestudy.data.model.MyDevice
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("devices")
    suspend fun devices(): Response<List<MyDevice>>

}