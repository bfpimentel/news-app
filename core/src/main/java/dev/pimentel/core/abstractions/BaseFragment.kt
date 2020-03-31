package dev.pimentel.core.abstractions

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment<ViewModelType : BaseContract.ViewModel>(
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {

    abstract val modules: List<Module>
    abstract val viewModel: ViewModelType

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(modules)
        bindViewModel()
    }

    open fun bindViewModel() {
        viewModel.isLoading().observe {
            // TODO
        }

        viewModel.isNotLoading().observe {
            // TODO
        }

        viewModel.error().observe {
            // TODO
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(modules)
    }

    protected inline fun <ObserverType> LiveData<ObserverType>.observe(
        crossinline observer: (ObserverType) -> Unit
    ) = observe(viewLifecycleOwner, Observer { observer(it) })
}
