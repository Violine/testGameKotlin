package com.korovin.alexander.www.orcavspenguin.Utils

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.ImageView

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SquareImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
        ImageView(context, attrs, defStyleAttr, defStyleRes) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) = super.onMeasure(widthMeasureSpec, widthMeasureSpec)
}