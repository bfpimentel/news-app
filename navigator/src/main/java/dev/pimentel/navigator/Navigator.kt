package dev.pimentel.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

interface FeedNavigator {
    fun testRoute()
}

interface NavigatorBinder {
    fun bind(navController: NavController)
    fun unbind()
}

interface Navigator : NavigatorBinder, FeedNavigator

class NavigatorImpl : Navigator {

    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        this.navController = null
    }

    override fun testRoute() {
        navController?.navigate(R.id.profile)
    }
}

data class NavigationRoute(
    @IdRes val navigationIdRes: Int,
    val bundle: Bundle? = null
)
