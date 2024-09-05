package com.asherpope.ccc_southlobbydisplay.ui.presentation.map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asherpope.ccc_southlobbydisplay.SLD
import com.asherpope.ccc_southlobbydisplay.data.SLDMap

@Composable
fun CardDisplayer(
    absoluteLoc: SLDMap.AbsoluteLocation? = null,
    forRestrictedWorkers: Boolean = false,
    onDismiss: () -> Unit
) {
    val server by SLD.appModule.server.collectAsState()
    val mapIdk = Any()
    var expandedId: String? by remember { mutableStateOf(null) }
    val populatedAbsoluteLoc = absoluteLoc?.contents?.filter { mapId ->
        var result = false
        for(record in server.tables.swData.records) {
            if (record.fields.mapId == mapId.code) {
                result = true
            }
        }
        result
    }

    synchronized(mapIdk) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onDismiss() }
        ) {
            if (absoluteLoc != null) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    if (populatedAbsoluteLoc != null) {
                        for (mapId in populatedAbsoluteLoc) {
                            if (mapId == populatedAbsoluteLoc.first()) {
                                Spacer(Modifier.height(128.dp))
                            } else {
                                Spacer(Modifier.height(48.dp))
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    mapId.displayName,
                                    style = MaterialTheme.typography.headlineLarge,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    modifier = Modifier
                                )

                                Spacer(Modifier.height(12.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .horizontalScroll(rememberScrollState())
                                ) {
                                    Spacer(Modifier.width(96.dp))
                                    val workers = server.tables.swData.records.filter { it.fields.mapId == mapId.code }
                                    for (worker in workers) {
                                        SupportedWorkerDisplayCard(
                                            worker = worker.fields,
                                            isExpanded = expandedId == worker.id
                                        ) { expandedId = if (expandedId == worker.id) { null } else { worker.id } }
                                    }
                                    Spacer(Modifier.width(96.dp))
                                }
                            }
                            if (mapId == populatedAbsoluteLoc.last()) {
                                Spacer(Modifier.height(128.dp))
                            }
                        }
                    }
                }
            } else if (forRestrictedWorkers) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "Restricted Workers",
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .horizontalScroll(rememberScrollState())
                        ) {
                            Spacer(Modifier.width(96.dp))
                            val workers = server.tables.swData.records.filter { it.fields.mapId == "RESTRICTED" }
                            for (worker in workers) {
                                SupportedWorkerDisplayCard(
                                    worker = worker.fields,
                                    isExpanded = expandedId == worker.id
                                ) { expandedId = if (expandedId == worker.id) { null } else { worker.id } }
                            }
                            Spacer(Modifier.width(96.dp))
                        }
                    }
                }

            } else {
                Text("No absolute location found.")
            }
        }
    }
}