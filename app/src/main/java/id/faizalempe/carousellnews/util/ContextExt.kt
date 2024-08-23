package id.faizalempe.carousellnews.util

import android.content.Context
import androidx.annotation.DimenRes

fun Context?.getDimen(@DimenRes id: Int) = this?.resources?.getDimensionPixelSize(id) ?: 0