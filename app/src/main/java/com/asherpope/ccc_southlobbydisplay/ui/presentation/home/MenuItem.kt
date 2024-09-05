package com.asherpope.ccc_southlobbydisplay.ui.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.data.MenuItemData
import com.asherpope.ccc_southlobbydisplay.data.NavRoutes
import com.asherpope.ccc_southlobbydisplay.ui.theme.SLD_ver02Theme
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun MenuItem(
    data: MenuItemData,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }

LaunchedEffect(Unit) {
    // animation features not stable, so using the same timed delay for all items
    delay(300)
    visible = true
}

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = data.resource,
                contentDescription = data.contentDescription,
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
                    .padding(bottom = 16.dp)
            )
            Text(data.label, color = Color.White, style = MaterialTheme.typography.headlineMedium)
        }
    }
}


@ExperimentalAnimationApi
@Preview
@Composable
fun MenuItemPreview() {
    SLD_ver02Theme {
        MenuItem(
            MenuItemData(
                "Map",
                painterResource(id = R.drawable.earth_ver03),
                "map icon",
                NavRoutes.MAP,
                500
            )
        )
    }
}
