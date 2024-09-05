package com.asherpope.ccc_southlobbydisplay.domain

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.asherpope.ccc_southlobbydisplay.SLD
import com.asherpope.ccc_southlobbydisplay.data.MapLoc
import com.asherpope.ccc_southlobbydisplay.data.SLDMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MapPageUiState(
    var selectedLocation: SLDMap.AbsoluteLocation? = null,
    var showingSelectedLocationCardDisplayer: Boolean = false,
    var showingRestrictedWorkerCardDisplayer: Boolean = false,
    var showingDarkOverlay: Boolean = false
)
class MapPageViewModel(): ViewModel() {
    private val lock = Any()
    private val server = SLD.appModule.server.value
    val populatedLocations: List<MapLoc> = SLDMap.ALL_LOCATIONS.filter { mapLoc ->
        var result = false
        for(record in server.tables.swData.records) {
            for(mapId in mapLoc.contents) {
                if(mapId.code == record.fields.mapId) result = true
            }
        }
        result
    }

    // Expose screen UI state
    private val _uiState = MutableStateFlow(MapPageUiState())
    val uiState: StateFlow<MapPageUiState> = _uiState.asStateFlow()

    private val absoluteLocations: MutableList<SLDMap.AbsoluteLocation> = mutableListOf()

    // Handle business logic
    fun constructAbsoluteLocations(usingSize: Size) {
        synchronized(lock) {
            for (i in SLDMap.ALL_LOCATIONS) {
                absoluteLocations.plusAssign(
                    SLDMap.AbsoluteLocation(
                        contents = i.contents,
                        topLeft = Offset(
                            x = usingSize.width * i.x,
                            y = usingSize.height * i.y
                        )
                    )
                )
            }
        }
    }

    fun dismissCardDisplayer() {
        _uiState.value = _uiState.value.copy(
            selectedLocation = null,
            showingSelectedLocationCardDisplayer = false,
            showingRestrictedWorkerCardDisplayer = false,
            showingDarkOverlay = false
        )
    }
    fun updateSelectedLocation(usingOffset: Offset, toggleButtonVisibility: (Boolean) -> Unit) {
        synchronized(lock) {
            if (absoluteLocations.isEmpty()) {
                Log.i("MapPageError", "No absolute locations defined.")
            } else {
                val matchedLoc = absoluteLocations.find { loc ->
                    // basically checking if the tap was inside the bounds of an existing map point
                    loc.topLeft.x < usingOffset.x
                            && usingOffset.x < loc.bottomRight.x
                            && loc.topLeft.y < usingOffset.y
                            && usingOffset.y < loc.bottomRight.y
                }

                if(matchedLoc != null) {
                    toggleButtonVisibility(false)
                    val newValue = !uiState.value.showingSelectedLocationCardDisplayer
                    _uiState.value = _uiState.value.copy(
                        selectedLocation = matchedLoc,
                        showingDarkOverlay = newValue,
                        showingSelectedLocationCardDisplayer = newValue
                    )
                }

                Log.i(
                    "MapPageVM",
                    _uiState.value.selectedLocation?.contents?.toString() ?: "No location matched"
                )

            }
        }
    }

    fun viewRestrictedWorkers(toggleButtonVisibility: (Boolean) -> Unit) {
        toggleButtonVisibility(false)
        val newValue = !uiState.value.showingRestrictedWorkerCardDisplayer
        _uiState.value = _uiState.value.copy(
            showingDarkOverlay = newValue,
            showingRestrictedWorkerCardDisplayer = newValue
        )
    }
}