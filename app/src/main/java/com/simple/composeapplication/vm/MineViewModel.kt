package com.simple.composeapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.composeapplication.model.ArticleBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MineViewModel : ViewModel() {
    val url = "https://t7.baidu.com/it/u=2454152781,2953854107&fm=3035&app=3035&size=re3,2&q=75&n=0&g=4n&f=JPEG&fmt=auto&maxorilen2heic=2000000?s=78EABD44CC5325C4921C18B90300C090"

    private val _list = MutableStateFlow<List<ArticleBean>>(emptyList())
    val list = _list.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getList()
    }

    fun getList() {
        val articles = ArrayList<ArticleBean>()
        for (i in 0 until 40) {
            val bean = ArticleBean(articleId = "id_$i", "作品 $i", url, 1000 + i)
            articles.add(bean)
        }
        _list.value = articles
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(800)
            getList()
            _isRefreshing.value = false
        }
    }
}
