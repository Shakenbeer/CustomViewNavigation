package com.shakenbeer.customviewnavigation

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost

class FrameLayoutHost @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),  NavHost {

    private val mNavController = NavController(context)

    override fun getNavController(): NavController {
        return mNavController
    }
}

