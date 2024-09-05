package com.asherpope.ccc_southlobbydisplay.data.remote

import com.asherpope.ccc_southlobbydisplay.data.remote.dto.ServerData
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface AirtableService {
    suspend fun getServerData(): ServerData?

    companion object {
        fun create(): AirtableService {
            return AirtableServiceImpl(
                client = HttpClient(Android) {
                    install(Logging)
                    install(ContentNegotiation) {
                        json(
                            Json {
                                prettyPrint = true
                                isLenient = true
                                ignoreUnknownKeys = true
                            }
                        )
                }
            }
            )
        }
    }
}