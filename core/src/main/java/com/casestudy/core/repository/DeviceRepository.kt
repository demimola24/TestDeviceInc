package com.casestudy.core.repository

import com.casestudy.data.model.MyDevice
import com.casestudy.domain.DataOutput
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    suspend fun fetchDevices(): Flow<DataOutput<List<MyDevice>>>
}