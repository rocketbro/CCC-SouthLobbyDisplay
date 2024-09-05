package com.asherpope.ccc_southlobbydisplay.ui.presentation.philosophy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary2
import com.asherpope.ccc_southlobbydisplay.ui.theme.SLD_ver02Theme

@Composable
fun PhilosophyPage(
    // option to run a code block when this composable is drawn
    onCreate: () -> Unit = { /* Empty lambda by default */ }
) {

    LaunchedEffect(Unit) {
        onCreate()
    }

    val bodyTextColor = Color.White.copy(0.7f)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
            //.background(Color.Red)
        ) {// IMAGE COLUMN
            Image(
                painterResource(id = R.drawable.philosophyofmissions_ver02),
                "ministries icon",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                //.size(128.dp)
            )
            Text(
                "Philosophy of\nMissions",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                //.background(Color.DarkGray)
            )
        }

        Spacer(Modifier.weight(0.45f))

        Column(
            modifier = Modifier
                .weight(1.7f)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(130.dp))

            Surface(
                color = Primary2,
                shape = RoundedCornerShape(28.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(18.dp)

                ) {// TEXT COLUMN
                    Text(
                        "Mission Statement",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                    Text(
                        stringResource(R.string.mission_statement),
                        style = MaterialTheme.typography.bodyMedium,
                        color = bodyTextColor
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        "About Faith Promise",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                    Text(
                        stringResource(R.string.about_faith_promise),
                        style = MaterialTheme.typography.bodyMedium,
                        color = bodyTextColor
                    )

                    Spacer(Modifier.height(4.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) { // members/giving
                            Image(
                                painterResource(id = R.drawable.members_giving_qr),
                                contentDescription = "qr code",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(128.dp)
                                    .padding(12.dp)
                            )
                            Text(
                                "Give to Faith Promise",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = bodyTextColor
                            )
                        }

                        Spacer(Modifier.weight(0.5f))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) { //faith promise commitment
                            Image(
                                painterResource(id = R.drawable.faith_promise_commitment_qr),
                                contentDescription = "qr code",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(128.dp)
                                    .padding(12.dp)
                            )
                            Text(
                                "Make a Faith Promise Commitment",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = bodyTextColor
                            )
                        }
                    }
                } // end of card column
            }

            Spacer(Modifier.height(240.dp))
        }

        Spacer(Modifier.weight(1f))
    }
}


@Preview(widthDp = 1920, heightDp = 1080)
@Composable
fun PhilosophyPagePreview() {
    SLD_ver02Theme {
        PhilosophyPage()
    }
}
