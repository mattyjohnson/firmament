package com.nomaditas.firmament.ext

import android.content.res.Resources

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()
