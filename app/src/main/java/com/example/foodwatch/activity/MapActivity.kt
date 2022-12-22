package com.example.foodwatch.activity

import android.app.Activity
import android.os.Bundle
import com.example.foodwatch.R
import com.example.foodwatch.databinding.ActivityMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapActivity : Activity() {

    //private lateinit var binding: ActivityMapBinding
    private lateinit var mapView: MapView

    companion object {
        private const val API_KEY = "9ed9bd48-dc52-4f93-a8df-063a3c8a9f55"
        private val TARGET_LOCATION = Point(54.187081, 45.215696)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMapBinding.inflate(layoutInflater)

        MapKitFactory.setApiKey(API_KEY)
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.mapView)
        mapView.map.move(
            CameraPosition(TARGET_LOCATION, 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f), null
        )
    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}