package com.example.synergyfinance

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LoanActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setTitle("Budget Calculator")

        drawer = findViewById(R.id.drawer_layout)
        var navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle (this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace (
                R.id.fragment_container,
                BudgetFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_budget)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_budget -> supportFragmentManager.beginTransaction().replace (
                R.id.fragment_container,
                BudgetFragment()
            ).commit()
            R.id.nav_loan -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                LoanFragment()
            ).commit()
            R.id.nav_logout -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                LogOutFragment()
            ).commit()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}