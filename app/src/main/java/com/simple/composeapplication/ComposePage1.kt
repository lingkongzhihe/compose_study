package com.simple.composeapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simple.composeapplication.ui.theme.ComposeApplicationTheme
import com.simple.composeapplication.vm.DataViewModel

@Composable
fun ComposePage1(name: String, modifier: Modifier = Modifier, viewModel: DataViewModel = viewModel()) {
//    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text("Item 1")
//        Text("Item 2")
//        Button(onClick = {}) { Text(name) }
//    }

//    Box(modifier = modifier.fillMaxSize()) {
//        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null, modifier = Modifier.width(300.dp).height(300.dp).align(Alignment.Center))
//        Text(text = name, modifier = Modifier.align(Alignment.Center))
//    }
//    val context = LocalContext.current.applicationContext
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing) ,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp)
//    ) {
//        items(30) { index ->
//            val color = if (index % 2 == 0) Color(0xFF87CEEB) else Color.Cyan
//            Text(
//                text = "$index",
//                color = Color.White,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .wrapContentHeight(
//                        Alignment.CenterVertically
//                    )
//                    .background(color = color)
//                    .clickable {
//                        Toast.makeText(context, "$index", Toast.LENGTH_SHORT).show()
//                    })
//        }
//    }

    val list by viewModel.list.collectAsStateWithLifecycle()
    val context = LocalContext.current.applicationContext

    // 进页面开一次协程，一直 collect 到离开页面
    LaunchedEffect(Unit) {
        viewModel.toastEvent.collect { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxHeight(0.2f)
            .windowInsetsPadding(WindowInsets.safeDrawing) ,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
    ) {
        itemsIndexed(list, key = { _, item -> item }) { index, item ->
            val color = if (index % 2 == 0) Color(0xFF87CEEB) else Color.Cyan
            Text(
                text = item,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(40.dp)
                    .fillMaxHeight()
                    .background(color = color)
                    .wrapContentHeight(
                        Alignment.CenterVertically
                    )
                    .clickable {
                        viewModel.onItemClick(item)
                    })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposePage1Preview() {
    ComposeApplicationTheme {
        ComposePage1(name = "Compose Page 1")
    }
}