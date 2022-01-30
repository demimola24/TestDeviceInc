package com.casestudy.testdevices.ui.views

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.casestudy.testdevices.R
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var appBarConfig: AppBarConfiguration

    private val navController: NavController
        get() = findNavController(R.id.mainNavHost)

    private val navItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        //close the drawers
        drawerLayout.closeDrawers()
        return@OnNavigationItemSelectedListener NavigationUI.onNavDestinationSelected(it, navController)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupUI()

    }
    

    private fun setupUI() {

        //set appbar config
        appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment), drawerLayout) {
            return@AppBarConfiguration true
        }

        //create drawer toggle, by now drawerLayout must have been created.
        drawerToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(drawerToggle)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (!appBarConfig.topLevelDestinations.contains(destination.id)) {
                drawerLayout.closeDrawers()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
        navigationView.setNavigationItemSelectedListener(navItemSelectedListener)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setupActionBarWithNavController(navController, appBarConfig)
        }
        
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem) = item.onNavDestinationSelected(navController)
            || super.onOptionsItemSelected(item)


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig)
    }

}
