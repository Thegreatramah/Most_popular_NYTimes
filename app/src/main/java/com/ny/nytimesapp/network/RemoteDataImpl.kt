package com.ny.nytimesapp.network

import PAGE_START
import PERIODS
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ny.nytimesapp.BuildConfig
import com.ny.nytimesapp.R
import com.ny.nytimesapp.model.ResultResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val resultResponseApi: ResultResponseApi) :
    RemoteData {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val noMoreMessage: MutableLiveData<Int> = MutableLiveData()

    override fun getArticles() {
        resultResponseApi.getPosts(PERIODS[PAGE_START], BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveResultListStart() }
                .doOnTerminate { onRetrieveResultListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { e -> onRetrieveResultListError() }
                )
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
            //resultAdapter.updateResultList(postList.results)
        }

    }

    private fun onRetrieveResultListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveResultListNoMoreMsg() {
        noMoreMessage.value = R.string.no_more_msg
    }


}