package com.vkuzub.googlemapcustommarkerview

import android.graphics.Bitmap
import com.bumptech.glide.load.Transformation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by Viacheslav on 01.12.2017.
 */
data class CustomMarkerItem(val name: String, val pos: LatLng, val imagePath: String,
                            val transformation: Transformation<Bitmap>) : ClusterItem {
    override fun getPosition() = pos

}