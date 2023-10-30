package com.example.recipes.utils

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

infix fun View.visibleIf(condition : Boolean){
    visibility = if(condition) View.VISIBLE else View.INVISIBLE
}

fun <T> LifecycleOwner.collectLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch { repeatOnLifecycle(Lifecycle.State.STARTED) { flow.collect(collect) } }
}