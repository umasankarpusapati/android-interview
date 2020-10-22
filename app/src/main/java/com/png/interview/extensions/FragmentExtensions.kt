package com.png.interview.extensions

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

fun Context.replaceFragment(fragment: Fragment, addToBackstack: Boolean = true) {
    if (this !is AppCompatActivity) {
        throw IllegalStateException("You must be in an AppCompatActivity to attach fragments")
    }
    /*(if (this.findViewById<ViewGroup>(R.id.fragment_container) == null) {
        throw IllegalStateException("You must have a container with id fragment_container to attach fragments")
    }
    val transaction = this.supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
    if (addToBackstack) {
        transaction.addToBackStack(null)
    }
    transaction
        .setTransition(TRANSIT_FRAGMENT_OPEN)
        .commit()*/
}

val Fragment.viewLifecycleScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope

fun Fragment.putString(key: String, value: String): Fragment {
    val bundle = this.arguments ?: Bundle()
    bundle.putString(key, value)
    this.arguments = bundle
    return this
}
