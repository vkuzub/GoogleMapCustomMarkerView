package com.vkuzub.googlemapcustommarkerview

import android.content.Context
import android.graphics.Color
import com.google.maps.android.ui.IconGenerator

/**
 * Created by Viacheslav on 01.12.2017.
 */
class IconGeneratorBackgroundless(context: Context?) : IconGenerator(context) {
    override fun setColor(color: Int) {
        super.setColor(Color.parseColor("#403F51B5"))
    }
}