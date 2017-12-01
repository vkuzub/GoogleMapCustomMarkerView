package com.vkuzub.googlemapcustommarkerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator

/**
 * Created by Viacheslav on 01.12.2017.
 */
class CustomMarkerRenderer(val context: Context?, map: GoogleMap?,
                           clusterManager: ClusterManager<CustomMarkerItem>?)
    : DefaultClusterRenderer<CustomMarkerItem>(context, map, clusterManager) {

    private var iconGenerator: IconGenerator
    private var markerImageView: ImageView

    init {
        val singleItem: View = LayoutInflater.from(context).inflate(R.layout.map_marker_circle, null, false)
        iconGenerator = IconGeneratorBackgroundless(context)
        iconGenerator.setContentView(singleItem)
        markerImageView = singleItem.findViewById(R.id.image)
    }

    override fun onClusterItemRendered(clusterItem: CustomMarkerItem?, marker: Marker?) {
        Glide.with(context).load(clusterItem?.imagePath).bitmapTransform(clusterItem?.transformation)
                .into(object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                        markerImageView.setImageDrawable(resource)
                        marker?.setIcon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon()))
                    }
                })


    }

    override fun shouldRenderAsCluster(cluster: Cluster<CustomMarkerItem>?) = false
}