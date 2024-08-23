package id.faizalempe.carousellnews.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import id.faizalempe.carousellnews.util.viewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected val binding: VB by viewBinding { inflateViewBinding() }

    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}