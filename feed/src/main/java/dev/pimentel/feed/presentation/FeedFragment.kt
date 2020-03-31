package dev.pimentel.feed.presentation

import dev.pimentel.core.abstractions.BaseFragment
import dev.pimentel.feed.R
import dev.pimentel.feed.feedModules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class FeedFragment : BaseFragment<FeedContract.ViewModel>(R.layout.feed_fragment_layout) {

    override val modules: List<Module> = feedModules
    override val viewModel: FeedContract.ViewModel by viewModel<FeedViewModel>()
}
