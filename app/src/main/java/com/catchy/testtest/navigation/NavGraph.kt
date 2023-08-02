package com.catchy.testtest.navigation

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.catchy.testtest.NewItem
import com.catchy.testtest.News
import com.catchy.testtest.model.NewItem
import com.catchy.testtest.viewModel.NewsViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val sharedViewModel = NewsViewModel()
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable(route = "list") {
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
                /* Using composable function */

                sharedViewModel.selectedNews?.let { it1 -> com.catchy.testtest.NewsDetail(newsItem = it1) }
        }
    }
}