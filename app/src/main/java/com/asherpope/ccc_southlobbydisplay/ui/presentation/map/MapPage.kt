package com.asherpope.ccc_southlobbydisplay.ui.presentation.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.domain.MapPageViewModel

@Composable
fun MapPage(
    mapPageViewModel: MapPageViewModel = viewModel(),
    toggleButtonVisibility: (Boolean) -> Unit,
    onCreate: (viewRestrictedWorkers: () -> Unit) -> Unit
) {
    val mapUiState by mapPageViewModel.uiState.collectAsState()
    val locationPointBitmap = ImageBitmap.imageResource(R.drawable.locationpoint_ver05)
    LaunchedEffect(Unit) {
        onCreate() { // view restricted workers
            mapPageViewModel.viewRestrictedWorkers(toggleButtonVisibility)
        }
    }

    Box(contentAlignment = Alignment.Center) {

        Image(
            painterResource(id = R.drawable.worldmap_ver04),
            "world map",
            modifier = Modifier
                .fillMaxWidth(0.96f)
                //.background(Color.White)
                .pointerInput(key1 = Unit) {
                    detectTapGestures(
                        onTap = { offset ->
                            mapPageViewModel.updateSelectedLocation(offset, toggleButtonVisibility)
                        }
                    )
                }
                .drawWithCache {
                    onDrawWithContent {
                        this@onDrawWithContent.drawContent()
                        // draw all the items in the supported worker loc list
                        for (i in mapPageViewModel.populatedLocations) {
                            drawImage(
                                image = locationPointBitmap,
                                topLeft = Offset(
                                    x = size.width * i.x,
                                    y = size.height * i.y
                                )
                            )
                        }
                        mapPageViewModel.constructAbsoluteLocations(size)
                    }
                }
        )
        AnimatedVisibility( // dark overlay
            visible = mapUiState.showingDarkOverlay,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.75f))
            )
        }

        AnimatedVisibility( // selected location
            visible = mapUiState.showingSelectedLocationCardDisplayer,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            CardDisplayer(absoluteLoc = mapUiState.selectedLocation) {
                mapPageViewModel.dismissCardDisplayer()
                toggleButtonVisibility(true)
            }
        }

        AnimatedVisibility( // restricted workers
            visible = mapUiState.showingRestrictedWorkerCardDisplayer,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            CardDisplayer(forRestrictedWorkers = true) {
                mapPageViewModel.dismissCardDisplayer()
                toggleButtonVisibility(true)
            }
        }
    }
}
//@Preview(widthDp = 16* previewScaleFactor, heightDp = 9* previewScaleFactor)
//@Composable
//fun MapPage_Preview() {
//    SLD_ver02Theme {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.background(color = Primary1)
//        ) {
//            MapPage() {string, function -> }
//        }
//    }
//}