package com.casestudy.data.remote

import com.casestudy.data.model.MyDevice
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getDevices(): Response<List<MyDevice>> = apiService.devices()

}