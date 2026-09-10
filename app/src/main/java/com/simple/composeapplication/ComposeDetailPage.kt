package com.simple.composeapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.simple.composeapplication.vm.DetailViewModel
@Composable
fun ComposeDetailPage(modifier: Modifier, viewModel: DetailViewModel = viewModel()) {
    val menus by viewModel.menus.collectAsStateWithLifecycle()
    val checkedIndex = remember { mutableIntStateOf(0) }
    val checkedMenuId = remember { mutableStateOf(menus[0].menuId) }
    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {
            itemsIndexed(menus, key = { _, item -> item.menuId }) { index, item ->
                Text(
                    text = item.menuName,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(if (index == checkedIndex.intValue) Color.Blue else Color.Black)
                        .clickable {
                            checkedIndex.intValue = index
                            checkedMenuId.value = item.menuId
                        }
                        .padding(start = 10.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }

        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            thickness = 1.dp,
            color = Color.White
        )

        Column(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
                .background(color = Color.White)
                .padding(start = 20.dp, top = 50.dp)
        ) {
            LaunchedEffect(checkedMenuId.value) {
                viewModel.changeMenu(checkedMenuId.value)
            }
            val detailInfo by viewModel.changeEvent.collectAsStateWithLifecycle(null)
            detailInfo?.let {
                val title = detailInfo?.title ?: ""
                val desc = detailInfo?.desc ?: ""
                val image = detailInfo?.image ?: ""
                Text(
                    title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    desc,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                SubcomposeAsyncImage(
                    model = image,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .align(Alignment.Start),
                    contentDescription = null,
                    loading = {
                        Box(modifier.fillMaxSize(0.2f), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = Color.LightGray)
                        }
                    },
                    error = {
                        Icon(Icons.Default.BrokenImage, contentDescription = "图片加载失败")
                    }
                )
            }
        }
    }
}