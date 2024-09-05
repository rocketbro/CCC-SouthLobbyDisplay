package com.asherpope.ccc_southlobbydisplay.data.remote

import android.util.Log
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.CIData
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.SWData
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.ServerData
import com.asherpope.ccc_southlobbydisplay.data.remote.dto.Tables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import java.time.Instant

class AirtableServiceImpl(
    private val client: HttpClient
) : AirtableService {
    override suspend fun getServerData(): ServerData? {
        Log.i("Airtable", "Calling Airtable. Time: ${Instant.now()}")

        // load supported worker data:
        val swData: SWData = try {
            client.get(Airtable.SUPPORTED_WORKERS) {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        Airtable.AUTHORIZATION
                    )
                }
            }.body()
        } catch (e: Exception) {
            println("Error: {${e.message}")
            return null
        }

        // load current info data:
        val ciData: CIData = try {
            client.get(Airtable.CURRENT_INFO) {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        Airtable.AUTHORIZATION
                    )
                }
            }.body()
        } catch (e: Exception) {
            println("Error: {${e.message}")
            return null
        }
        val tables = Tables(swData, ciData)
        return ServerData(tables)
    }
}