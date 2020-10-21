package com.example.mvvmmultimodulearchitecture.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmmultimodulearchitecture.R
import com.example.mvvmmultimodulearchitecture.domain.viewmodel.AppSettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val appSettingViewModel: AppSettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ten -> appSettingViewModel.savePageShopCount10()
            R.id.twenty -> appSettingViewModel.savePageShopCount20()
        }
        return true
    }
}