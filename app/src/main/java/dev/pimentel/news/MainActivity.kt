package dev.pimentel.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        NavigationUI.setupWithNavController(
            main_bottom_navigation,
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
        )
    }
}
