package com.asherpope.ccc_southlobbydisplay.ui.presentation.home


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.data.MenuItemData
import com.asherpope.ccc_southlobbydisplay.data.NavRoutes

@ExperimentalComposeApi
@ExperimentalAnimationApi
@Composable
fun HomePage(
    onCreate: () -> Unit = { /* Empty lambda by default */ },
    onButtonClick: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val menuOptions = listOf(
        MenuItemData(
            "News",
            painterResource(id = R.drawable.missionreport_ver02),
            "mission report icon",
            NavRoutes.MISSION_REPORT,
            500
        ),
        MenuItemData(
            "Map",
            painterResource(id = R.drawable.earth_ver03),
            "map icon",
            NavRoutes.MAP,
            750
        ),
        MenuItemData(
            "Philosophy",
            painterResource(id = R.drawable.philosophyofmissions_ver02),
            "philosophy icon",
            NavRoutes.PHILOSOPHY_OF_MISSIONS,
            1000
        ),
    )

    LaunchedEffect(Unit) {
        onCreate()
//        delay(2000) // this delay lets the UI load before calling up Airtable
//        SLD.appModule.refreshServerData()
    }

    LazyRow {
        items(items = menuOptions, key = { menuOptions.indexOf(it) } ) { it ->
            MenuItem(
                data = it,
                modifier = Modifier
                    .padding(horizontal = 42.dp)
                    .clickable(interactionSource = interactionSource, indication = null) { onButtonClick(it.navRoute) }
                    // animation features not usable with current stable Gradle plugin:
                    //.animateItemPlacement()
            )

        }
    }
}
