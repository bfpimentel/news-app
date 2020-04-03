package dev.pimentel.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dev.pimentel.navigator.NavigatorBinder
import dev.pimentel.news.databinding.MainActivityLayoutBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: NavigatorBinder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
    }

    override fun onResume() {
        super.onResume()

        val binding = MainActivityLayoutBinding.inflate(layoutInflater)
        val navController = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)!!
            .findNavController()

        NavigationUI.setupWithNavController(
            binding.mainBottomNavigation,
            navController
        )

        navigator.bind(navController)
    }

    override fun onPause() {
        super.onPause()
        navigator.unbind()
    }
}
