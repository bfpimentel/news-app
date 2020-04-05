package dev.pimentel.core.abstractions

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

interface ViewBindingHolder<T : ViewBinding> {
    fun initBinding(binding: T, fragment: BaseFragment<*, T>, onBind: T.() -> Unit): View
    fun unbindView()
}

class ViewBindingHolderImpl<T : ViewBinding> : ViewBindingHolder<T>, LifecycleObserver {

    private var binding: T? = null
    private var lifecycle: Lifecycle? = null

    private lateinit var fragmentName: String

    override fun initBinding(
        binding: T,
        fragment: BaseFragment<*, T>,
        onBind: T.() -> Unit
    ): View {
        this.binding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        fragmentName = fragment::class.simpleName ?: "N/A"
        binding.apply(onBind)
        return binding.root
    }

    @Suppress("Unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun unbindView() {
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }
}
