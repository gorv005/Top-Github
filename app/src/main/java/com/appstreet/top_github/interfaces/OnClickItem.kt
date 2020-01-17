package com.appstreet.top_github.interfaces

interface OnClickItem<T> {
    fun onClick(position:Int, t:T?=null)
}