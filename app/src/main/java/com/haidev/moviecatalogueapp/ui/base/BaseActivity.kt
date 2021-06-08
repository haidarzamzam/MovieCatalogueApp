package com.haidev.moviecatalogueapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<out Any>> :
    AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    private lateinit var dataBinding: T
    private lateinit var rootView: View
    private val baseViewModel by lazy { getViewModels() }

    @LayoutRes
    abstract fun setLayout(): Int

    abstract fun getViewModels(): V

    abstract fun onReadyAction()

    open fun onObserveAction() = Unit

    open fun onClearArguments() = Unit

    open fun onFragmentDestroyed() = Unit

    open fun getViewDataBinding() = dataBinding

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        dataBinding = DataBindingUtil.setContentView(this, setLayout())
        rootView = dataBinding.root
        dataBinding.executePendingBindings()
        onReadyAction()
        onObserveAction()
    }

    override fun onPause() {
        super.onPause()
        onClearArguments()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun changeOrientation(orientation: Int) {
        requestedOrientation = orientation
    }
}