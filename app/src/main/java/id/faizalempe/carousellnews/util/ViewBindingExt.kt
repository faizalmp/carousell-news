package id.faizalempe.carousellnews.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <VB: ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> VB
): Lazy<VB> = lazy(LazyThreadSafetyMode.NONE) { getViewBinding(bindingInflater) }

inline fun <VB: ViewBinding> AppCompatActivity.getViewBinding(
    crossinline bindingInflater: (LayoutInflater) -> VB
): VB = bindingInflater.invoke(layoutInflater)

inline fun <VB: ViewBinding> ViewGroup.getItemViewBinding(
    crossinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
): VB = bindingInflater.invoke(LayoutInflater.from(context), this, false)