package com.appstreet.top_github.utils

import android.view.View

enum class VisiblityEnum(val status:Int) {
    Hide(View.GONE),Show(View.VISIBLE),InVisible(View.INVISIBLE)
}