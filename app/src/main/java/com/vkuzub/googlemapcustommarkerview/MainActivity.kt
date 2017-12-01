package com.vkuzub.googlemapcustommarkerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import jp.wasabeef.glide.transformations.CropCircleTransformation
import jp.wasabeef.glide.transformations.MaskTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


val TAG = "MainActivity"
val DEFAULT_LOCATION = LatLng(51.507, -0.127)

class MainActivity : AppCompatActivity(), OnMapReadyCallback,
        ClusterManager.OnClusterItemClickListener<CustomMarkerItem> {

    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<CustomMarkerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMap()
    }

    private fun initMap() {
        val mapFragment: SupportMapFragment? = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 14f))

        initClusterRenderer()
    }

    private fun initClusterRenderer() {
        clusterManager = ClusterManager(this, googleMap)
        clusterManager.renderer = CustomMarkerRenderer(this, googleMap, clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)
        clusterManager.setOnClusterItemClickListener(this)

        addFakeItems()
    }

    private fun addFakeItems() {
        clusterManager.addItems(getFakeItems())
        clusterManager.cluster()
    }

    private fun getFakeItems(): ArrayList<CustomMarkerItem> {
        return arrayListOf(
                CustomMarkerItem("item1", LatLng(51.507, -0.127), "https://avatars0.githubusercontent.com/u/9257131?s=460&v=4", CropCircleTransformation(this)),
                CustomMarkerItem("item2", LatLng(51.517, -0.127), "https://avatars0.githubusercontent.com/u/9257131?s=460&v=4", RoundedCornersTransformation(this, 10, 2)),
                CustomMarkerItem("item3", LatLng(51.507, -0.117), "https://avatars0.githubusercontent.com/u/9257131?s=460&v=4", MaskTransformation(this, R.drawable.mask_starfish))
        )
    }

    override fun onClusterItemClick(item: CustomMarkerItem?): Boolean {
        Toast.makeText(this, item?.name, Toast.LENGTH_SHORT).show()
        return false
    }

}
