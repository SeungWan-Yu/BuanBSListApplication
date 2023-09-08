package com.buan.buanbslistapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    // LiveData를 사용하여 관찰하고자 하는 데이터를 선언합니다.
    private val _data = MutableLiveData<Int>()
    val data: LiveData<Int>
        get() = _data

    // 데이터를 변경하는 함수를 제공합니다.
    fun updateData(newData: Int) {
        _data.value = newData
    }
}