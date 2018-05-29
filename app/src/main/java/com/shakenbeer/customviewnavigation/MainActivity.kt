package com.shakenbeer.customviewnavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customViewNavigator = CustomViewNavigator(container)
        //TODO make this work
        //val navController = Navigation.findNavController(container)
        container.navController.navigatorProvider.addNavigator(customViewNavigator)
        container.navController.navigate(R.id.start)
    }
}