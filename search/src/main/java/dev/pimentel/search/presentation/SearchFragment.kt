package dev.pimentel.search.presentation

import dev.pimentel.core.abstractions.BaseFragment
import dev.pimentel.search.R
import dev.pimentel.search.databinding.SearchFragmentLayoutBinding
import dev.pimentel.search.searchModules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class SearchFragment : BaseFragment<SearchContract.ViewModel, SearchFragmentLayoutBinding>(
    R.layout.search_fragment_layout
) {
    override val modules: List<Module> = searchModules
    override val viewModel: SearchContract.ViewModel by viewModel<SearchViewModel>()

    override fun bindView() = initBinding(
        SearchFragmentLayoutBinding.inflate(layoutInflater),
        this
    ) { }
}
