package dev.pimentel.profile

import dev.pimentel.profile.presentation.ProfileContract
import dev.pimentel.profile.presentation.ProfileViewModel
import dev.pimentel.profile.shared.ViewModelTest
import org.junit.Assert.assertNotNull
import org.junit.Test

class ProfileViewModelTest : ViewModelTest<ProfileContract.ViewModel>() {

    override lateinit var viewModel: ProfileContract.ViewModel

    @Test
    override fun setupSubject() {
        viewModel = ProfileViewModel()

        assertNotNull(viewModel)
    }
}
