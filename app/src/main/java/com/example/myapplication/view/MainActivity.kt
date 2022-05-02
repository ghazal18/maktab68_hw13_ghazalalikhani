package com.example.myapplication.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.myapplication.R
import com.example.myapplication.viewModel.ViewModelAccount
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: ViewModelAccount by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            when (it.itemId) {
                R.id.nav_profile -> navController.navigate(R.id.profileFragment)
                R.id.nav_selectAccount -> navController.navigate(R.id.selectAccountFragment)
                R.id.nav_showAccount -> navController.navigate(R.id.showAccountFragment)
                R.id.nav_createAccount -> navController.navigate(R.id.createAccountFragment)
                R.id.nav_deleteAll -> dialogBeSure()
            }
            true
        }


    }

    private fun dialogBeSure() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Are you sure you want to delete all your information?")

            .setCancelable(true)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                viewModel.deleteAll()
                Toast.makeText(this, "All of your Account deleted", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("No ", DialogInterface.OnClickListener { dialog, id ->

            })
        val alert = dialogBuilder.create()
        alert.setTitle("WARNING")
        alert.setCanceledOnTouchOutside(true)
        alert.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}