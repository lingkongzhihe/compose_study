package com.simple.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.simple.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.launch

private object Routes {
    const val HOME = "home"
    const val IM = "im/{userId}"
    fun im(userId: String) = "im/$userId"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                AppNav()
            }
        }
    }
}

@Composable
private fun AppNav() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()
    val showingIm = currentEntry?.destination?.route == Routes.IM
    Box(modifier = Modifier.fillMaxSize()) {
        MainTabs(
            onOpenIm = { userId ->
                navController.navigate(Routes.im(userId))
            }
        )
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = if (showingIm) Modifier.fillMaxSize() else Modifier
        ) {
            composable(
                route = Routes.HOME,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { }
            composable(
                route = Routes.IM,
                arguments = listOf(navArgument("userId") { type = NavType.StringType }),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) { entry ->
                val userId = entry.arguments?.getString("userId").orEmpty()
                ImPage(
                    userId = userId,
                    modifier = Modifier.fillMaxSize(),
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun MainTabs(onOpenIm: (userId: String) -> Unit) {
    val tabs = listOf("首页", "分类", "我的", "自定义控件")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Default.Home
                                    1 -> Icons.Default.Category
                                    2 -> Icons.Default.Person
                                    else -> Icons.Default.DashboardCustomize
                                },
                                contentDescription = title
                            )
                        },
                        label = { Text(title, fontSize = 12.sp) },
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = tabs.size - 1,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) { page ->
            when (page) {
                0 -> ComposePage1(
                    name = "第一个compose页面",
                    modifier = Modifier.fillMaxSize()
                )
                1 -> ComposeDetailPage(modifier = Modifier.fillMaxSize())
                2 -> MinePage(
                    modifier = Modifier.fillMaxSize(),
                    onOpenIm = { onOpenIm("me") }
                )
                else -> CustomView(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        Greeting("Android")
    }
}
