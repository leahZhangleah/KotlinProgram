package com.leahhumlelu.kotlinprogram.realestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class Status{
    LOADING,FAILURE,SUCCESS
}

class RealEstateOverviewViewModel : ViewModel() {

    private var _response = MutableLiveData<List<MarsProperty>>()
    val response:LiveData<List<MarsProperty>>
        get() = _response

    private var _status = MutableLiveData<Status>()
    val status:LiveData<Status>
        get() = _status

    init {
        getMarsRealEstateProperties(PropertyFilter.SHOW_ALL)
    }
    fun getMarsRealEstateProperties(filter: PropertyFilter){
        viewModelScope.launch {
            try {
                _status.value=Status.LOADING
                val properties = RealEstateApi.service.getRealEstateOverview(filter.type)
                if(properties.isNotEmpty()){
                    _response.value = properties
                }
                _status.value=Status.SUCCESS
            }catch (e:Throwable){
                _status.value = Status.FAILURE
                _response.value = arrayListOf()
            }
        }
    }
}