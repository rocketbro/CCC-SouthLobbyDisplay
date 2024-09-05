package com.asherpope.ccc_southlobbydisplay.ui.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.collectAsState
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
import com.asherpope.ccc_southlobbydisplay.SLD
import com.asherpope.ccc_southlobbydisplay.data.CIChannels
import com.asherpope.ccc_southlobbydisplay.data.previewScaleFactor
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.CIFields
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.SWFields
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.SWRecord
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary1
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary2
import com.asherpope.ccc_southlobbydisplay.ui.theme.Primary3
import com.asherpope.ccc_southlobbydisplay.ui.theme.SLD_ver02Theme

@Composable
fun NewsPage(
    onCreate: () -> Unit = { }
) {
    val server = SLD.appModule.server.collectAsState().value
    val allFeaturedWorkers: List<SWRecord> = server.tables.swData.records.filter { it.fields.isFeatured }
    val featuredWorker: SWFields = if(allFeaturedWorkers.isNotEmpty()) {
        allFeaturedWorkers.first().fields
    } else {
        server.tables.swData.records.first().fields
    }
    val news: CIFields = server.tables.ciData.records.first { it.fields.channel == CIChannels.NEWS }.fields
    val events: CIFields = server.tables.ciData.records.first { it.fields.channel == CIChannels.EVENTS }.fields
    val bodyTextColor = Color.White.copy(0.7f)

    LaunchedEffect(Unit) {
        onCreate()
    }

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
                painterResource(id = R.drawable.missionreport_ver02),
                "scroll icon",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                //.size(128.dp)
            )
            Text(
                "Latest News",
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

            if(!news.isHidden or !events.isHidden) {
                Surface(
                    color = Primary2,
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(18.dp)

                    ) {
                        if(!news.isHidden) {
                            Text(
                                "News from Outreach & Missions",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                            )
                            Text(
                                news.value,
                                style = MaterialTheme.typography.bodyMedium,
                                color = bodyTextColor,
                                modifier = Modifier
                            )
                            Spacer(Modifier.height(4.dp))
                        }

                        if(!events.isHidden) {
                            Text(
                                "Current & Upcoming Events",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                            )
                            Text(
                                events.value,
                                style = MaterialTheme.typography.bodyMedium,
                                color = bodyTextColor,
                                modifier = Modifier
                            )
                            Spacer(Modifier.height(4.dp))
                        }


                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(Primary2)
            ) {

                Surface(
                    color = Primary3,
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                    ) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(featuredWorker.image?.get(0)?.url)
                                .crossfade(true)
                                .build(),
                            contentDescription = "supported worker picture",
                            placeholder = painterResource(id = R.drawable.placeholder_ver02),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(4 / 3f)
                                .clip(RoundedCornerShape(28.dp))
                        )

                        Text(
                            featuredWorker.name,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                        Text(
                            "Supported Workers of the Week",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                        )
                        Text(
                            featuredWorker.description,
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }

                Spacer(Modifier.weight(0.125f))


                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .padding(vertical = 34.dp)
                ) {

                    Text(
                        featuredWorker.bio,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Prayer Requests",
                        color = Color.White.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                    Text(
                        featuredWorker.prayerRequests,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(Modifier.height(240.dp))
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview(widthDp = 16* previewScaleFactor, heightDp = 9* previewScaleFactor)
@Composable
fun MapPage_Preview() {
    SLD_ver02Theme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Primary1)
        ) {
            NewsPage()
        }
    }
}
