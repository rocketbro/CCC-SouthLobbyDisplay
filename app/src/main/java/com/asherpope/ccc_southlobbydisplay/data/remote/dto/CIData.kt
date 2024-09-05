package com.asherpope.ccc_southlobbydisplay.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CIData(
    val records: List<CIRecord>
)
