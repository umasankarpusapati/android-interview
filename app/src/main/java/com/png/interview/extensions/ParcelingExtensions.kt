package com.png.interview.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

inline fun <reified T : Parcelable> Activity.getParcelableNullable(): T? = this.intent.getParcelableExtra(T::class.java.name)

inline fun <reified T : Parcelable> Activity.getParcelable(): T = this.intent.getParcelableExtra(T::class.java.name)

fun <T : Parcelable> Intent.putParcelable(parcelable: T?) = this.apply {
    parcelable?.let {
        this.putExtra(parcelable.javaClass.name, parcelable)
    }
}

fun <T : Parcelable> Fragment.putParcelable(parcelable: T?) = this.apply {
    val bundle = this.arguments ?: Bundle()
    parcelable?.let {
        bundle.putParcelable(parcelable.javaClass.name, parcelable)
    }
    this.arguments = bundle
}

inline fun <reified T : Parcelable> Fragment.getParcelable(): T = this.arguments?.getParcelable(T::class.java.name)
    ?: throw IllegalStateException("no parcel passed in for object ${T::class.java.name}")

inline fun <reified T : Parcelable> Fragment.getParcelableNullable(): T? = this.arguments?.getParcelable(T::class.java.name)
