package com.appstreet.top_github.interfaces

import android.widget.ImageView
import android.widget.TextView

interface OnClickItem<T> {
    fun onClick(position:Int, t:T?=null, imageView: ImageView, username: TextView,name: TextView)
}