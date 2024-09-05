package com.asherpope.ccc_southlobbydisplay.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SWFields(
    val name: String = "Contact the O&M department for more information.",
    val description: String = "Contact the O&M department for more information.",
    val bio: String = "Contact the O&M department for more information.",
    val mapId: String = "RESTRICTED",
    val prayerRequests: String = "Contact the O&M department for more information.",
    val image: List<RemoteImage>? = null,
    val isFeatured: Boolean = false
)
