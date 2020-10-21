package com.example.mvvmmultimodulearchitecture.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mvvmmultimodulearchitecture.R
import com.example.mvvmmultimodulearchitecture.domain.dto.Shop
import com.example.mvvmmultimodulearchitecture.ui.fragment.HotPepperListFragmentDirections
import kotlinx.android.synthetic.main.list_hot_pepper_row.view.*

class HotPepperShopListAdapter(private val shopList: List<Shop>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_hot_pepper_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            shopList[position].apply {
                shopNameView.text = shopName
                addressView.text = address
                shopImageView.load(logoImage)
            }
            itemView.setOnClickListener {
                HotPepperListFragmentDirections.actionHotPepperListFragmentToHotPepperDetailFragment(shopList[position]).apply {
                    it.findNavController().navigate(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val shopNameView: TextView = itemView.shopNameView
    val addressView: TextView = itemView.addressView
    val shopImageView: ImageView = itemView.shopImageView
}