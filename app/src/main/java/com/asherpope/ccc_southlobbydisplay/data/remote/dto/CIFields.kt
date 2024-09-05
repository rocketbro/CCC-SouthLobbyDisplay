package com.asherpope.ccc_southlobbydisplay.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CIFields(
    val channel: String = "No channel found",
    val value: String = "No value found",
    val isHidden: Boolean = false
)
