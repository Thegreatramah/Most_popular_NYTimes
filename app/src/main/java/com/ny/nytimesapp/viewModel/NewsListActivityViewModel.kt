package com.ny.nytimesapp.viewModel

import PAGE_START
import PERIODS
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ny.nytimesapp.BuildConfig
import com.ny.nytimesapp.R
import com.ny.nytimesapp.model.ResultResponse
import com.ny.nytimesapp.network.ResultResponseApi
import com.ny.nytimesapp.view.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListActivityViewModel @Inject constructor(private val resultResponseApi: ResultResponseApi) : ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val noMoreMessage: MutableLiveData<Int> = MutableLiveData()
    val newsAdapter: NewsAdapter = NewsAdapter()
    val errorClickListener = View.OnClickListener { loadResult() }

    //use for add and clear all observer
    private val compositeDisposable = CompositeDisposable()


    fun loadResult() {
        if (PAGE_START < PERIODS.size) {
            compositeDisposable.add(resultResponseApi.getPosts(PERIODS[PAGE_START], BuildConfig.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveResultListStart() }
                    .doOnTerminate { onRetrieveResultListFinish() }
                    .subscribe(
                            { result -> onRetrievePostListSuccess(result) },
                            { e -> onRetrieveResultListError() }
                    ))

        } else {
            onRetrieveResultListNoMoreMsg()
        }
    }


    private fun onRetrieveResultListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveResultListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: Any) {
        if (postList is ResultResponse) {
            newsAdapter.updateResultList(postList.results)
        }

    }

    private fun onRetrieveResultListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveResultListNoMoreMsg() {
        noMoreMessage.value = R.string.no_more_msg
    }

    /**
     * Clear all Observer when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}