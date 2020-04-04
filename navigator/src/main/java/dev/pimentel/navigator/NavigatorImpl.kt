package dev.pimentel.navigator

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.navOptions

interface FeedNavigator {
    fun routeToProfile()
}

interface NavigatorBinder {
    fun bind(navController: NavController)
    fun unbind()
}

interface Navigator : NavigatorBinder, FeedNavigator

internal class NavigatorImpl : Navigator {

    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        this.navController = null
    }

    override fun routeToProfile() {
        navigate(R.id.profile)
    }

    private fun navigate(
        @IdRes destinationId: Int,
        args: Pair<String, Any?>? = null
    ) {
        val navOptions = navOptions {
            anim {
                enter = android.R.anim.fade_in
                exit = android.R.anim.fade_out
                popEnter = android.R.anim.fade_in
                popExit = android.R.anim.fade_out
            }
        }

        navController?.navigate(
            destinationId,
            args?.let { bundleOf(it) },
            navOptions
        )
    }
}
