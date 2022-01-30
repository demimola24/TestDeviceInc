package com.casestudy.testdevices.ui.views.home

import com.casestudy.core.repository.DeviceRepository
import com.casestudy.data.model.MyDevice
import com.casestudy.domain.DataOutput
import com.casestudy.domain.Status
import com.casestudy.testdevices.BaseViewModelTest
import com.casestudy.testdevices.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Demimola on 1/30/22.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest:  BaseViewModelTest()  {

    @Mock
    private lateinit var deviceRepository: DeviceRepository

    private lateinit var viewModel: HomeViewModel


    @Before
    fun setUp() {
        viewModel = HomeViewModel(deviceRepository)
    }


    @Test
    fun `Test fetchUsers should trigger loading state`() = runBlockingMainTest {
        //GIVEN
        val tempData  = DataOutput.loading(null)
        val flowQuestions = flowOf(tempData)

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(deviceRepository).fetchDevices()
        viewModel.fetchDevices()

        //THEN
        assert(viewModel.devices.value?.status == Status.LOADING)
    }

    @Test
    fun `Test fetchUsers should emit data`() = runBlockingMainTest {
        //GIVEN
        val tempData  = DataOutput.success("", emptyList<MyDevice>())
        val flowQuestions = flowOf(tempData)

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(deviceRepository).fetchDevices()
        viewModel.fetchDevices()

        //THEN
        assert(viewModel.devices.value == tempData)
    }

    @Test
    fun `Test fetchUsers should return Success`() = runBlockingMainTest {
        //GIVEN
        val tempData  = DataOutput.success("", null)
        val flowQuestions = flowOf(tempData)

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(deviceRepository).fetchDevices()
        viewModel.fetchDevices()

        //THEN
        assert(viewModel.devices.value?.status == Status.SUCCESS)
    }


    @Test
    fun `Test fetchUsers should return Error`() = runBlockingMainTest {
        //GIVEN
        val tempData  = DataOutput.error("",500, null)
        val flowQuestions = flowOf(tempData)

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(deviceRepository).fetchDevices()
        viewModel.fetchDevices()

        //THEN
        assert(viewModel.devices.value?.status == Status.ERROR)
    }
}