package com.casestudy.core.repository

import com.casestudy.core.utils.NetworkHelper
import com.casestudy.data.model.MyDevice
import com.casestudy.data.repository.DataRepository
import com.casestudy.domain.DataOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val dataRepository: DataRepository,
    private val networkHelper: NetworkHelper
) : DeviceRepository {

    override suspend fun fetchDevices(): Flow<DataOutput<List<MyDevice>>> {
        return flow {
            if(networkHelper.isNetworkConnected()){
                emit(DataOutput.loading(null))
                val result = dataRepository.getDevices()
                if(result.isSuccessful){
                    emit(DataOutput.success("Success",result.body()))
                }else{
                    emit(DataOutput.error(result.message(),result.code(),result.body()))
                }
            }else{
                emit(DataOutput.error("No internet connection",0,null))
            }

        }.flowOn(Dispatchers.IO)
    }
}