package com.ny.nytimesapp.nytimes

import com.ny.nytimesapp.BuildConfig
import com.ny.nytimesapp.mockData.DummyDataGenerator
import com.ny.nytimesapp.network.ResultResponseApi
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewsActivityViewModelTest {

    @Mock
    val resultResponseApi: ResultResponseApi = TODO()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

//    @Test
//    fun getNewsResultSuccess() {
//        val list  = DummyDataGenerator.getResultList()
//        Mockito.`when`(resultResponseApi.getPosts(7, BuildConfig.API_KEY))
//                .thenReturn(list)
//        assert(list.byline != null)
//    }
//
//    @Test
//    fun getNewsError() {
//        val list = DummyDataGenerator.getResultList()[14]
//        Mockito.`when`(resultResponseApi.getPosts(14, BuildConfig.API_KEY))
//                .thenReturn(list)
//        Assert.assertNull(list)
//    }
}
