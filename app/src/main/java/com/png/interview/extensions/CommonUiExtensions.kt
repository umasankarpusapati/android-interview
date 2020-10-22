package com.png.interview.extensions

import android.view.LayoutInflater
import android.view.View

fun View.getLayoutInflater() = LayoutInflater.from(this.context)
