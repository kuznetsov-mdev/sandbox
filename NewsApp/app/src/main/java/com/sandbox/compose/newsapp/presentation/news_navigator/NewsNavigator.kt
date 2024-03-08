package com.sandbox.compose.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sandbox.compose.newsapp.R
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.presentation.bookmark.BookmarkScreen
import com.sandbox.compose.newsapp.presentation.bookmark.BookmarkViewModel
import com.sandbox.compose.newsapp.presentation.details.DetailsEvent
import com.sandbox.compose.newsapp.presentation.details.DetailsScreen
import com.sandbox.compose.newsapp.presentation.details.DetailsViewModel
import com.sandbox.compose.newsapp.presentation.home.HomeScreen
import com.sandbox.compose.newsapp.presentation.home.HomeViewModel
import com.sandbox.compose.newsapp.presentation.navgraph.Route
import com.sandbox.compose.newsapp.presentation.news_navigator.component.BottomNavigationItem
import com.sandbox.compose.newsapp.presentation.news_navigator.component.NewsBottomNavigation
import com.sandbox.compose.newsapp.presentation.search.SearchScreen
import com.sandbox.compose.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_search,
                text = "Search"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_bookmark,
                text = "Bookmark"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { idx ->
                        when (idx) {
                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )

                            else -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    searchEvent = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }


                navController.previousBackStackEntry?.savedStateHandle?.get<ArticleDto?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            articleDto = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value

                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

//navigation with parameters
private fun navigateToDetails(navController: NavController, article: ArticleDto) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}