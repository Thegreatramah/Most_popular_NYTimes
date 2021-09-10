package com.ny.nytimesapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ny.nytimesapp.view.NewsListActivity
import com.ny.nytimesapp.view.adapter.NewsAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsListActivityEspressoTest {

    @Before
    fun launchActivity() {
        ActivityScenario.launch(NewsListActivity::class.java)
    }

    @JvmField
    @Rule
    val testRule = ActivityTestRule<NewsListActivity>(NewsListActivity::class.java)

    @Test
    fun initialViewsDisplayedProperly() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.recyelerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewClick() {
        // Click item at position 3
        onView(withId(R.id.recyelerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ViewHolder>(3, click()))
    }


}