package com.asherpope.ccc_southlobbydisplay.data

import androidx.compose.ui.geometry.Offset

data class MapId(
    val code: String,
    val displayName: String
)
data class MapLoc(
    val contents: List<MapId>,
    val x: Float,
    val y: Float
)
object SLDMap {
    // USA
    val CT_NJ_PA_USA = MapLoc(
        contents = listOf(
            MapId("CT_USA", "Connecticut, USA"),
            MapId("NJ_USA", "New Jersey, USA"),
            MapId("PA_USA", "Pennsylvania, USA"),
        ),
        x = 0.248f,
        y = 0.325f
    )
    val FL_USA = MapLoc(
        contents = listOf(
            MapId("FL_USA", "Florida, USA")
        ),
        x = 0.228f,
        y = 0.41f
    )
    val MI_AND_OH_USA = MapLoc(
        contents = listOf(
            MapId("MI_USA", "Michigan, USA"),
            MapId("OH_USA", "Ohio, USA")
        ),
        x = 0.2275f,
        y = 0.3f
    )
    val NC_SC_VA_USA = MapLoc(
        contents = listOf(
            MapId("NC_USA", "North Carolina, USA"),
            MapId("SC_USA", "South Carolina, USA"),
            MapId("VA_USA", "Virginia, USA")
        ),
        x = 0.2375f,
        y = 0.36f
    )
    val ND_USA = MapLoc(
        contents = listOf(
            MapId("ND_USA", "North Dakota, USA"),
        ),
        x = 0.19f,
        y = 0.275f
    )
    val OK_USA = MapLoc(
        contents = listOf(
            MapId("OK_USA", "Oklahoma, USA")
        ),
        x = 0.18f,
        y = 0.35f
    )
    val TN_KY_USA = MapLoc(
        contents = listOf(
            MapId("KY_USA", "Kentucky, USA"),
            MapId("TN_USA", "Tennessee, USA"),
        ),
        x = 0.218f,
        y = 0.36f
    )
    val UT_USA = MapLoc(
        contents = listOf(
            MapId("UT_USA", "Utah, USA"),
        ),
        x = 0.15f,
        y = 0.34f
    )
    val WA_USA = MapLoc(
        contents = listOf(
            MapId("WA_USA", "Washington, USA"),
        ),
        x = 0.133f,
        y = 0.29f
    )

    // EU & UK
    val FRANCE_EU = MapLoc(
        contents = listOf(
            MapId("FRANCE_EU", "France"),
        ),
        x = 0.46f,
        y = 0.28f
    )
    val GERMANY_EU = MapLoc(
        contents = listOf(
            MapId("GERMANY_EU", "Germany"),
            MapId("BELGIUM_EU", "Belgium"),
        ),
        x = 0.48f,
        y = 0.25f
    )
    val GREECE_EU = MapLoc(
        contents = listOf(
            MapId("GREECE_EU", "Greece"),
        ),
        x = 0.519f,
        y = 0.35f
    )
    val SLOVAKIA_EU = MapLoc(
        contents = listOf(
            MapId("SLOVAKIA_EU", "Slovakia"),
        ),
        x = 0.51f,
        y = 0.265f
    )
    val ENGLAND_SCOTLAND_UK = MapLoc(
        contents = listOf(
            MapId("ENGLAND_UK", "England"),
            MapId("SCOTLAND_UK", "Scotland")
        ),
        x = 0.449f,
        y = 0.225f
    )

    // AFRICA
    val CAPETOWN_SOUTHAFRICA = MapLoc(
        contents = listOf(
            MapId("CAPETOWN_SOUTHAFRICA", "Cape Town, South Africa"),
        ),
        x = 0.515f,
        y = 0.815f
    )
    val JOHANNESBURG_SOUTHAFRICA = MapLoc(
        contents = listOf(
            MapId("JOHANNESBURG_SOUTHAFRICA", "Johannesburg, South Africa"),
        ),
        x = 0.54f,
        y = 0.78f
    )
    val KENYA = MapLoc(
        contents = listOf(
            MapId("KENYA", "Kenya"),
        ),
        x = 0.568f,
        y = 0.62f
    )
    val MOROCCO = MapLoc(
        contents = listOf(
            MapId("MOROCCO", "Morocco"),
        ),
        x = 0.441f,
        y = 0.38f
    )
    val NIGER = MapLoc(
        contents = listOf(
            MapId("NIGER", "Niger"),
        ),
        x = 0.48f,
        y = 0.5f
    )
    val UGANDA = MapLoc(
        contents = listOf(
            MapId("UGANDA", "Uganda"),
        ),
        x = 0.55f,
        y = 0.6f
    )
    val ZIMBABWE = MapLoc(
        contents = listOf(
            MapId("ZIMBABWE", "Zimbabwe"),
        ),
        x = 0.541f,
        y = 0.71f
    )

    // ASIA & MIDDLE EAST
    val CHINA = MapLoc(
        contents = listOf(
            MapId("CHINA", "China"),
        ),
        x = 0.735f,
        y = 0.37f
    )
    val IRAQ = MapLoc(
        contents = listOf(
            MapId("IRAQ", "Iraq"),
        ),
        x = 0.585f,
        y = 0.4f
    )
    val JAPAN = MapLoc(
        contents = listOf(
            MapId("JAPAN", "Japan"),
        ),
        x = 0.842f,
        y = 0.335f
    )
    val JORDAN = MapLoc(
        contents = listOf(
            MapId("JORDAN", "Jordan"),
        ),
        x = 0.558f,
        y = 0.405f
    )
    val LAOS = MapLoc(
        contents = listOf(
            MapId("LAOS", "Laos"),
        ),
        x = 0.75f,
        y = 0.475f
    )
    val MALAYSIA = MapLoc(
        contents = listOf(
            MapId("MALAYSIA", "Malaysia"),
        ),
        x = 0.752f,
        y = 0.58f
    )
    val UAE = MapLoc(
        contents = listOf(
            MapId("UAE", "United Arab Emirates"),
        ),
        x = 0.615f,
        y = 0.44f
    )

    // OTHERS
    val HAITI = MapLoc(
        contents = listOf(
            MapId("HAITI", "Haiti"),
        ),
        x = 0.255f,
        y = 0.476f
    )
    val LETHBRIDGE_ALBERTA_CANADA = MapLoc(
        contents = listOf(
            MapId("LETHBRIDGE_ALBERTA_CANADA", "Lethbridge, Alberta, Canada"),
        ),
        x = 0.168f,
        y = 0.26f
    )

    // Add MapLoc vals to the list below to draw them onscreen
    val ALL_LOCATIONS = listOf(

        // USA
        CT_NJ_PA_USA, FL_USA, MI_AND_OH_USA, NC_SC_VA_USA,
        ND_USA, OK_USA, TN_KY_USA, UT_USA, WA_USA,

        // EU
        FRANCE_EU, GERMANY_EU, GREECE_EU, SLOVAKIA_EU, ENGLAND_SCOTLAND_UK,

        // AFRICA
        CAPETOWN_SOUTHAFRICA, JOHANNESBURG_SOUTHAFRICA, KENYA, MOROCCO,
        NIGER, UGANDA, ZIMBABWE,

        // ASIA & MIDDLE EAST
        CHINA, IRAQ, JAPAN, JORDAN, LAOS, MALAYSIA, UAE,

        // OTHERS
        HAITI, LETHBRIDGE_ALBERTA_CANADA,
    )

    data class AbsoluteLocation(
        val contents: List<MapId>,
        val topLeft: Offset
    ) {
        val bottomRight: Offset = Offset(
            x = topLeft.x + 40,
            y = topLeft.y + 40
        )
    }

}

// scale preview depending on working screen
const val previewScaleFactor = 60
//@Preview(widthDp = 16* previewScaleFactor, heightDp = 9* previewScaleFactor)
//@Composable
//fun MapPage_Preview() {
//    SLD_ver02Theme {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.background(color = Primary1)
//        ) {
//            MapPage() {string, function -> }
//        }
//    }
//}