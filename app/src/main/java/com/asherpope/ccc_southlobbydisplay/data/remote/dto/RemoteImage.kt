package com.asherpope.ccc_southlobbydisplay.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val filename: String
)
