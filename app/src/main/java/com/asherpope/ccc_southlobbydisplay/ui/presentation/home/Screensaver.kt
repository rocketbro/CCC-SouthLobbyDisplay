package com.asherpope.ccc_southlobbydisplay.ui.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary1
import kotlinx.coroutines.delay


@Composable
fun Screensaver(
    onCreate: () -> Unit = { /* Empty lambda by default */ },
    onDismiss: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        onCreate()
        delay(500)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Surface(
            color = Primary1,
            modifier = Modifier
                .fillMaxSize()
                .clickable(interactionSource = interactionSource, indication = null) { onDismiss() }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(Modifier.weight(1f))

                Image(
                    painterResource(id = R.drawable.missionconflogo),
                    "Church Logo",
                    Modifier
                        .weight(3f)
                )

                Spacer(Modifier.weight(0.25f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Outreach & Missions",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                    )
                    Text(
                        "Touch anywhere to Begin",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(0.7f)
                    )
                }

                Spacer(Modifier.weight(0.75f))
            }
        }
    }
}