package com.ny.nytimesapp.view

import PAGE_START
import TOTAL_PAGES
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ny.nytimesapp.viewModel.factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.ny.nytimesapp.NyTimesApp
import com.ny.nytimesapp.R
import com.ny.nytimesapp.databinding.LayoutMainBinding
import com.ny.nytimesapp.utils.PaginationScrollListener
import com.ny.nytimesapp.viewModel.NewsListActivityViewModel
import javax.inject.Inject


class NewsListActivity : AppCompatActivity() {
    private var errorSnackbar: Snackbar? = null
    private var noMoreSnackbar: Snackbar? = null
    private lateinit var binding: LayoutMainBinding
    private lateinit var viewModel: NewsListActivityViewModel
    private var isLastPage = false
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var isLoading = true
    private val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NyTimesApp).getApplicationComponent().plusActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.layout_main)
        binding.recyelerView.layoutManager = linearLayoutManager
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListActivityViewModel::class.java)
        viewModel.loadResult()
        viewModel.errorMessage.observe(this, Observer { errorMsg -> if (errorMsg != null) showError(errorMsg) else hideError() })
        viewModel.noMoreMessage.observe(this, Observer { noMoreMsg -> if (noMoreMsg != null) showNoMoreMsg(noMoreMsg) else hideNoMore() })
        binding.viewModel = viewModel
        //set Pagination
        setScrollListner(binding.recyelerView, linearLayoutManager)

    }

    private fun setScrollListner(recyelerView: RecyclerView?, linearLayoutManager: LinearLayoutManager) {
        recyelerView?.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                PAGE_START++
                viewModel.loadResult()
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isPaginationValid(): Boolean {
                return if (PAGE_START < TOTAL_PAGES) true else false
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }


    private fun showNoMoreMsg(@StringRes errorMessage: Int) {
        noMoreSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        noMoreSnackbar?.setAction(R.string.dismiss, View.OnClickListener { hideNoMore() })
        noMoreSnackbar?.show()
    }

    private fun hideNoMore() {
        noMoreSnackbar?.dismiss()
    }
}