package com.simple.composeapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val _list = MutableStateFlow<List<String>>(emptyList())
    val list = _list.asStateFlow()

    private val _menus = MutableStateFlow<List<String>>(emptyList())
    val menus = _list.asStateFlow()

    // 一次性事件：不缓存，晚订阅收不到旧 Toast
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            _list.value = listOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "99",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29"
            )
        }
    }



    fun onItemClick(item: String) {
        viewModelScope.launch {
            _toastEvent.emit(item)
        }
    }
}
