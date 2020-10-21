package com.example.mvvmmultimodulearchitecture.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.mvvmmultimodulearchitecture.App
import com.example.mvvmmultimodulearchitecture.R
import com.example.mvvmmultimodulearchitecture.domain.viewmodel.HotPepperListViewModel
import com.example.mvvmmultimodulearchitecture.ui.view.HotPepperShopListAdapter
import kotlinx.android.synthetic.main.fragment_hot_pepper_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HotPepperListFragment: Fragment(R.layout.fragment_hot_pepper_list) {

    private val viewModel: HotPepperListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        searchWordInput.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)

        viewModel.findAllSearchHistories()
    }

    private fun observeViewModel() {

        searchWordInput.setOnEditorActionListener { textView, _, event ->
            if(event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    textView.windowToken,
                    0
                )
                val searchWord = textView.text.toString()
                viewModel.findShop(searchWord)
                viewModel.saveSearchHistory(searchWord)
            }
            return@setOnEditorActionListener false
        }

        viewModel.observeShopList(viewLifecycleOwner) {
            shopList.apply {
                adapter = HotPepperShopListAdapter(it)
                setHasFixedSize(true)
                adapter?.notifyDataSetChanged()
            }
        }

        viewModel.observeSearchHistories(viewLifecycleOwner) { searchHistories ->
            searchWordInput.apply {
                setAdapter(ArrayAdapter(App.context, android.R.layout.simple_dropdown_item_1line, searchHistories.map { it.searchWord }))
                Handler(Looper.getMainLooper()).postDelayed({
                    searchWordInput.showDropDown()
                }, 300)
            }
        }
    }
}