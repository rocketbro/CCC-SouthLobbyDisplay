package com.asherpope.ccc_southlobbydisplay.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CIRecord(
    val id: String,
    val createdTime: String,
    val fields: CIFields
)
