package dev.pimentel.profile.presentation

import dev.pimentel.core.abstractions.BaseFragment
import dev.pimentel.profile.R
import dev.pimentel.profile.databinding.ProfileFragmentLayoutBinding
import dev.pimentel.profile.profileModules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class ProfileFragment : BaseFragment<ProfileContract.ViewModel, ProfileFragmentLayoutBinding>(
    R.layout.profile_fragment_layout
) {
    override val modules: List<Module> = profileModules
    override val viewModel: ProfileContract.ViewModel by viewModel<ProfileViewModel>()

    override fun bindView() = initBinding(
        ProfileFragmentLayoutBinding.inflate(layoutInflater),
        this
    ) { }
}
