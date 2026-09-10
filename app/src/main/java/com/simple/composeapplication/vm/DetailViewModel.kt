package com.simple.composeapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.composeapplication.model.DetailInfo
import com.simple.composeapplication.model.DetailMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _menus = MutableStateFlow<List<DetailMenu>>(emptyList())
    val menus = _menus.asStateFlow()

    private val _changeEvent = MutableSharedFlow<DetailInfo>()
    val changeEvent = _changeEvent.asSharedFlow()

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            val menuNames = listOf(
                "单位", "灯光", "驾驶安全与辅助", "语言", "雷达泊车", "声音", "温度", "车门"
            )
            val menu = menuNames.mapIndexed { index, name -> DetailMenu("menu_$index", name) }
            _menus.emit(menu)
        }
    }

    fun changeMenu(menuId: String) {
        val url = "https://t7.baidu.com/it/u=2454152781,2953854107&fm=3035&app=3035&size=re3,2&q=75&n=0&g=4n&f=JPEG&fmt=auto&maxorilen2heic=2000000?s=78EABD44CC5325C4921C18B90300C090"
        viewModelScope.launch {
            delay(500)
            val detailInfo = DetailInfo(menuId, "详情标题$menuId", "详情描述$menuId", url)
            _changeEvent.emit(detailInfo)
        }
    }
}
