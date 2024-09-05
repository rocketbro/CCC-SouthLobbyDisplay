package com.asherpope.ccc_southlobbydisplay.ui.presentation.philosophy

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.SLD

@Composable
fun GetInvolvedPage(
    onCreate: () -> Unit = { /* Empty lambda by default */ }
) {
    LaunchedEffect(Unit) {
        onCreate()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Get Involved",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 12.dp)
            )
        Text("Scan the QR code to connect with the\nOutreach & Missions department.",
            color = Color.White.copy(0.7f),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Image(
            painterResource(id = R.drawable.getinvolvedqr_bcd5eb),
            contentDescription = "qr code",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(256.dp)
                .padding(36.dp)
                .clickable { SLD.appModule.refreshServerData() }
        )
    }
}