package com.casestudy.data.repository

import com.casestudy.data.remote.ApiHelper
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiHelper: ApiHelper) {
    /**
     * Manage all possible datSources
     */
    suspend fun getDevices() =  apiHelper.getDevices()
}