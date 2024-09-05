package com.asherpope.ccc_southlobbydisplay.ui.presentation.map

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asherpope.ccc_southlobbydisplay.R
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.SWFields
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary3
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary4
import com.asherpope.ccc_southlobbydisplay.ui.theme.SLD_ver02Theme

@Composable
fun SupportedWorkerDisplayCard(
    worker: SWFields,
    isExpanded: Boolean = false,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = isExpanded,
        transitionSpec = {
            (scaleIn()).togetherWith(scaleOut())
        },
        contentAlignment = Alignment.Center,
        label = "Card Animator",
    ) { expanded ->
        Card(
            shape = RoundedCornerShape(if (expanded) 28.dp else 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Primary3,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 2.dp
            ),
            modifier = Modifier
                .animateContentSize(animationSpec = tween(durationMillis = 300))
                .width(if (expanded) 350.dp else 200.dp)
                .clickable { onClick() }
            //.height(250.dp)
        ) {
            if(!expanded) { // collapsed layout
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(worker.image?.get(0)?.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "supported worker picture",
                        placeholder = painterResource(id = R.drawable.placeholder_ver04),
                        fallback = painterResource(id = R.drawable.placeholder_ver04),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(4 / 3f)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            worker.name,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )
                        Text(
                            if(worker.mapId == "RESTRICTED") "Tap to read more" else worker.description,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                        )
                        if(worker.mapId != "RESTRICTED") {
                            Text(
                                "Tap to read more",
                                color = Color.White.copy(0.5f),
                                //fontStyle = FontStyle.Italic,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                            )
                        }

                    }
                }
            } else { // expanded layout
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(28.dp))
                            .background(Primary4)
                            .fillMaxWidth()

                    ) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(worker.image?.get(0)?.url)
                                .crossfade(true)
                                .build(),
                            contentDescription = "supported worker picture",
                            placeholder = painterResource(id = R.drawable.placeholder_ver04),
                            fallback = painterResource(id = R.drawable.placeholder_ver04),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                //.width(400.dp)
                                .aspectRatio(6 / 4f)
                                .clip(RoundedCornerShape(28.dp))

                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {


                            Text(
                                worker.name,
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                worker.description,
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                            )
                            Spacer(Modifier.height(6.dp))

                        }
                    }
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                worker.bio,
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                            )

                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun SupportedWorkerDisplayCardPreview() {
    SLD_ver02Theme {
        SupportedWorkerDisplayCard(worker = SWFields(
            name = "John & Jane Doe",
            description = "Serving with Christ Covenant Church in Charlotte North Carolina",
            bio = "Mark is the Executive Director of Metanoia Prison Ministries which seeks to engage, educate and equip the church for the discipleship, mentoring, and reintegration of prisoners.\n" +
                    "\n" +
                    "Prisoners are one of the most underserved people groups in the world. There are approximately 150,000 evangelical Christians in prisons in the U.S. Metanoia seeks to disciple and mentor them.",
            prayerRequests = "Please pray that Asher would get this app done soon so people can see that he has not been wasting all these hours."
        ),
            isExpanded = false
        ) { }
    }
}
