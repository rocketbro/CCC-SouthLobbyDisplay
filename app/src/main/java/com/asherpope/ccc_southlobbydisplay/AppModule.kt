package com.asherpope.ccc_southlobbydisplay

import com.asherpope.ccc_southlobbydisplay.data.remote.AirtableService
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.ServerData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

interface AppModule {
    fun refreshServerData()

    val server: StateFlow<ServerData>
}
class AppModuleImpl(): AppModule {
    private val _server = MutableStateFlow(
        runBlocking {
            AirtableService.create().getServerData() ?: ServerData()
        }
    )
    override val server: StateFlow<ServerData> = _server.asStateFlow()

    override fun refreshServerData() {
        _server.update {
            runBlocking {
                AirtableService.create().getServerData() ?: ServerData()
            }
        }
    }
}