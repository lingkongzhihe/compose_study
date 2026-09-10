package com.simple.composeapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.simple.composeapplication.vm.MineViewModel

@Composable
fun MinePage(modifier: Modifier, viewModel: MineViewModel = viewModel()) {
    Column(
        modifier = modifier
            .padding(top = 40.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "我的",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubcomposeAsyncImage(
            model = "https://gips3.baidu.com/it/u=1148997845,2755062458&fm=3074&app=3074&f=PNG?w=2048&h=2048",
            contentDescription = "头像",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
                .size(48.dp)
                .border(2.dp, Color.LightGray, CircleShape)
                .clip(CircleShape),
            loading = {
                Box(modifier.size(48.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.LightGray)
                }
            },
            error = {
                Icon(Icons.Default.BrokenImage, contentDescription = "图片加载失败")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFDCDCDC), RoundedCornerShape(12.dp))
                .padding(12.dp)
                .align(alignment = Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Icon(Icons.Default.AddLocation, contentDescription = null)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "广州", fontSize = 12.sp, color = Color.Black)
            }
            Column {
                Icon(Icons.Default.AddCircle, contentDescription = null)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "粉丝", fontSize = 12.sp, color = Color.Black)
            }
            Column {
                Icon(Icons.Default.AddReaction, contentDescription = null)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "关注", fontSize = 12.sp, color = Color.Black)
            }
            Column {
                Icon(Icons.Default.AccountBalanceWallet, contentDescription = null)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "钱包", fontSize = 12.sp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            "我的作品",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        val articles by viewModel.list.collectAsStateWithLifecycle()
        LazyVerticalGrid(
            GridCells.Fixed(3),
            modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(articles, key = { _, item -> item.articleId }) { _, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = item.articleImage,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        contentDescription = null
                    )
                    Text(
                        item.articleTitle, modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 8.dp, end = 8.dp),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Icon(Icons.Default.Public, contentDescription = null)
                        Text(
                            "${item.lookCount}",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}