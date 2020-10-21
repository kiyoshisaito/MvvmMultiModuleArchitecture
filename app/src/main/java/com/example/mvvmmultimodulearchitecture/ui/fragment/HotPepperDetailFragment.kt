package com.example.mvvmmultimodulearchitecture.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mvvmmultimodulearchitecture.R
import kotlinx.android.synthetic.main.fragment_hot_pepper_detail.*

class HotPepperDetailFragment: Fragment(R.layout.fragment_hot_pepper_detail) {

    private val args :HotPepperDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        args.shop.apply {
            shopNameView.text = shopName
            addressView.text = address
            shopImageView.load(logoImage)
        }
    }
}