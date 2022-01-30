package com.casestudy.data.remote

import com.casestudy.data.model.MyDevice
import retrofit2.Response

interface ApiHelper {

    suspend fun getDevices(): Response<List<MyDevice>>
}