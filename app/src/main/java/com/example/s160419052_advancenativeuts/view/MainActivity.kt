package com.example.s160419052_advancenativeuts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.s160419052_advancenativeuts.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var controllerNavigation: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controllerNavigation = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, controllerNavigation, menuDrawer)
        NavigationUI.setupWithNavController(navMenu, controllerNavigation)
        bottomMenu.setupWithNavController(controllerNavigation)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(controllerNavigation, menuDrawer) || super.onSupportNavigateUp()
    }
}