package dev.pimentel.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

interface Navigator {
    fun bind(navController: NavController)
    fun unbind()

    fun navigate(route: NavigationRoute)
    fun pop()
}

class NavigatorImpl : Navigator {
    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        this.navController = null
    }

    override fun navigate(route: NavigationRoute) {
        navController?.navigate(
            route.navigationIdRes,
            route.bundle
        )
    }

    override fun pop() {
        navController?.popBackStack()
    }
}

data class NavigationRoute(
    @IdRes val navigationIdRes: Int,
    val bundle: Bundle? = null
)
