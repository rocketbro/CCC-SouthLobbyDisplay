package com.asherpope.ccc_southlobbydisplay.ui.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun TopMenu(
    showBackButton: Boolean,
    backButtonIcon: Painter,
    backButtonAction: () -> Unit,
    secondaryButtonText: String,
    secondaryButtonAction: () -> Unit,
    secondaryButtonIsVisible: Boolean
) {
    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.padding(all = 28.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AnimatedVisibility(
                visible = showBackButton,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                OutlinedIconButton(
                    onClick = { backButtonAction() },
                    border = BorderStroke(1.dp, Color.White.copy(0.75f)),
                    interactionSource = MutableInteractionSource(),
                    colors = IconButtonDefaults.outlinedIconButtonColors(
                        contentColor = Color.White
                    )
                    ) {
                    Image(
                        painter = backButtonIcon,
                        contentDescription = "back"
                    )
                }
            }

            AnimatedVisibility(
                visible = secondaryButtonIsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                OutlinedButton(
                    onClick = { secondaryButtonAction() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White.copy(0.75f)
                    )
                ) {
                    Text(secondaryButtonText)
                }
            }
        }
        Spacer(Modifier.fillMaxSize())
    }
}