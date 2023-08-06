package com.catchy.xmlParser.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.catchy.xmlParser.ui.composable.News
import com.catchy.xmlParser.ui.composable.NewsDetail
import com.catchy.xmlParser.viewModel.NewsViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val sharedViewModel = NewsViewModel()
    NavHost(
        navController = navController,
        startDestination = "newsList"
    ) {
        composable(route = "newsList") {
            /* Using composable function */
            News(onNavigate = {
                navController.currentBackStackEntry?.arguments?.putString("url", "test")
                navController.navigate(
                    "details?url=${
                        Uri.encode("test")
                    }"
                )
            }, sharedViewModel = sharedViewModel)
        }
        composable(
            route = "details?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) {
            sharedViewModel.selectedNews?.let { it1 -> NewsDetail(newsItem = it1) }
        }
    }
}