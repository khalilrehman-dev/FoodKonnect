package com.khalil.foodkonnect.ui.common

import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

// Shared setup logic for any screen using an osmdroid MapView —
// keeps configuration consistent instead of repeating it per-screen.
object MapViewUtils {

    // Default center point: Rawalpindi/Islamabad area, used until
    // a real user location is available.
    val DEFAULT_CENTER = GeoPoint(33.6844, 73.0479)
    const val DEFAULT_ZOOM = 14.0

    fun setupMapView(mapView: MapView, centerOn: GeoPoint = DEFAULT_CENTER) {
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(DEFAULT_ZOOM)
        mapView.controller.setCenter(centerOn)
    }
}