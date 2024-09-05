package com.asherpope.ccc_southlobbydisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asherpope.ccc_southlobbydisplay.data.NavRoutes
import com.asherpope.ccc_southlobbydisplay.ui.presentation.philosophy.GetInvolvedPage
import com.asherpope.ccc_southlobbydisplay.ui.presentation.home.HomePage
import com.asherpope.ccc_southlobbydisplay.ui.presentation.home.Screensaver
import com.asherpope.ccc_southlobbydisplay.ui.presentation.map.MapPage
import com.asherpope.ccc_southlobbydisplay.ui.presentation.navigation.TopMenu
import com.asherpope.ccc_southlobbydisplay.ui.presentation.news.NewsPage
import com.asherpope.ccc_southlobbydisplay.ui.presentation.philosophy.PhilosophyPage
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary1
import com.asherpope.ccc_southlobbydisplay.ui.theme.SLD_ver02Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SLD_ver02Theme {
                val navController = rememberNavController()
                var showBackButton by remember { mutableStateOf(false) }
                var previousRoute by remember { mutableStateOf(NavRoutes.SCREENSAVER) }
                var iconId by remember { mutableIntStateOf(R.drawable.round_arrow_back_24) }
                var secondaryButtonText by remember { mutableStateOf("Undefined") }
                var secondaryButtonAction by remember { mutableStateOf({ }) }
                var secondaryButtonIsVisible by remember { mutableStateOf(false) }

                // Define a box for the background
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Primary1)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.SCREENSAVER,
                        enterTransition = { scaleIn() },
                        exitTransition = { scaleOut() }
                    ) {
                        composable(NavRoutes.SCREENSAVER) {
                            Screensaver(onCreate = {
                                showBackButton = false
                            }) {
                                navController.navigate(NavRoutes.HOME)
                            }
                        }
                        composable(NavRoutes.HOME) {
                            HomePage(onCreate = {
                                previousRoute = NavRoutes.SCREENSAVER
                                showBackButton = true
                                secondaryButtonIsVisible = false
                            }) { pageRouteId ->
                                navController.navigate(pageRouteId)
                            }
                        }
                        composable(NavRoutes.MISSION_REPORT) {
                            NewsPage() {
                                previousRoute = NavRoutes.HOME
                            }
                        }
                        composable(NavRoutes.MAP) {
                            MapPage(
                                toggleButtonVisibility = { shouldShow ->
                                    secondaryButtonIsVisible = shouldShow
                                    showBackButton = shouldShow
                                },
                                onCreate = { viewRestrictedWorkers ->
                                    previousRoute = NavRoutes.HOME
                                    secondaryButtonText = getString(R.string.view_restricted)
                                    secondaryButtonAction = { viewRestrictedWorkers() }
                                    secondaryButtonIsVisible = true
                                }
                            )
                        }
                        composable(NavRoutes.PHILOSOPHY_OF_MISSIONS) {
                            PhilosophyPage(onCreate = {
                                previousRoute = NavRoutes.HOME
                                secondaryButtonText = getString(R.string.get_involved)
                                secondaryButtonAction = { navController.navigate(NavRoutes.GET_INVOLVED) }
                                secondaryButtonIsVisible = true
                            })
                        }
                        composable(NavRoutes.GET_INVOLVED) {
                            GetInvolvedPage(onCreate = {
                                previousRoute = NavRoutes.PHILOSOPHY_OF_MISSIONS
                                secondaryButtonIsVisible = false
                            })
                        }

                    }

                    TopMenu(
                        showBackButton = showBackButton,
                        backButtonIcon = painterResource(id = iconId),
                        backButtonAction = { navController.navigate(previousRoute) },
                        secondaryButtonText = secondaryButtonText,
                        secondaryButtonAction = secondaryButtonAction,
                        secondaryButtonIsVisible = secondaryButtonIsVisible
                    )



                    // trying to change icon when nav destination changes
//                    iconId = if(navController.currentDestination == NavDestination(NavRoutes.HOME)) {
//                        R.drawable.round_close_24
//                    } else {
//                        R.drawable.round_arrow_back_24
//                    }

                }
            }
        }
    }
}
