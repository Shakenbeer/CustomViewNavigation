package com.shakenbeer.customviewnavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(container)

        navController.addOnNavigatedListener { _, destination ->
            if (destination.id == R.id.start) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                findViewById<Button>(R.id.button).setOnClickListener {
                    navController.navigate(R.id.stage_one)
                }
            }
            if (destination.id == R.id.stage_one) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                findViewById<Button>(R.id.button).setOnClickListener {
                    navController.navigate(R.id.stage_two)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }
}