package com.casestudy.core.repository

import com.casestudy.core.utils.NetworkHelper
import com.casestudy.data.remote.ApiHelper
import com.casestudy.data.repository.DataRepository
import com.casestudy.domain.DataOutput
import com.casestudy.domain.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Created by Demimola on 1/30/22.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DeviceRepositoryImplTest {

    @Mock
    private lateinit var apiHelper: ApiHelper
    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var dataRepository: DataRepository
    private lateinit var dataImpl: DeviceRepositoryImpl

    @Before
    fun setUp() {
        dataRepository = DataRepository(apiHelper)
        dataImpl = DeviceRepositoryImpl(dataRepository,networkHelper)
    }


    @Test
    fun `Test first event emitted is loading event`() = runBlocking {
        //GIVEN
        Mockito.`when`(networkHelper.isNetworkConnected()).thenReturn(true)
        Mockito.`when`(dataRepository.getDevices()).thenReturn(Response.success(emptyList()))

        //WHEN
        val flow = dataImpl.fetchDevices()

        //THEN
        assert(flow.first()== DataOutput.loading(null))

    }

    @Test
    fun `Test last event emitted is Success event`() = runBlocking {
        //GIVEN
        Mockito.`when`(networkHelper.isNetworkConnected()).thenReturn(true)
        Mockito.`when`(dataRepository.getDevices()).thenReturn(Response.success(null))

        //WHEN
        val flow = dataImpl.fetchDevices()

        //THEN
        assert(flow.last().status== Status.SUCCESS)

    }

    @Test
    fun `Test error is returned when internet is down`() = runBlocking {
        //GIVEN
        Mockito.`when`(networkHelper.isNetworkConnected()).thenReturn(false)
        Mockito.`when`(dataRepository.getDevices()).thenReturn(Response.success(null))

        //WHEN
        val flow = dataImpl.fetchDevices()

        //THEN
        assert(flow.last().status==Status.ERROR)

    }
}