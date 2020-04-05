package dev.pimentel.search

import dev.pimentel.search.presentation.SearchContract
import dev.pimentel.search.presentation.SearchViewModel
import dev.pimentel.search.shared.ViewModelTest
import org.junit.Assert.assertNotNull
import org.junit.Test

internal class SearchViewModelTest : ViewModelTest<SearchContract.ViewModel>() {

    override lateinit var viewModel: SearchContract.ViewModel

    @Test
    override fun setupSubject() {
        viewModel = SearchViewModel()

        assertNotNull(viewModel)
    }
}
