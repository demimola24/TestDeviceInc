package com.casestudy.testdevices.ui.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.casestudy.core.repository.DeviceRepository
import com.casestudy.data.model.MyDevice
import com.casestudy.domain.DataOutput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val _devices = MutableLiveData<DataOutput<List<MyDevice>>>()
    val devices: LiveData<DataOutput<List<MyDevice>>>
        get() = _devices


    fun fetchDevices() {
        viewModelScope.launch {
            deviceRepository.fetchDevices().collect {
                _devices.value = it
            }
        }
    }
}