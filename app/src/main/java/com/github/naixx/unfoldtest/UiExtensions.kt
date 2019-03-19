package com.github.naixx.unfoldtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

@JvmOverloads
@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean = false): T {
    return LayoutInflater.from(this.context).inflate(resource, this, attachToRoot) as T
}
