package com.example.tugas5.helper

import android.content.res.TypedArray
import androidx.fragment.app.Fragment
import com.example.tugas5.R

object Constant {
    fun getSplashAssets(fragment: Fragment): ArrayList<Splash> {
        val assetsList = ArrayList<Splash>()

        val image: TypedArray = fragment.resources.obtainTypedArray(R.array.kubo)
        val text: Array<String> = fragment.resources.getStringArray(R.array.splash_message)
        val widget: Array<String> = fragment.resources.getStringArray(R.array.widget)


        for (i in 0..2) {
            assetsList.add(Splash(image.getResourceId(i, -1), text[i], widget[i]))
        }

        image.recycle()

        return assetsList
    }
}