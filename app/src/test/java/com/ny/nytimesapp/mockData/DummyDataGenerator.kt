package com.ny.nytimesapp.mockData
import com.ny.nytimesapp.model.Result


object DummyDataGenerator {
    fun getResultList(): List<Result> {
        var m1= Result("By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times", "https://www.nytimes.com/2020/09/22/world/covid-coronavirus.html")

        var m2 = Result("By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times", "https://www.nytimes.com/2020/09/22/world/covid-coronavirus.html")

        var m3 = Result( "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times", "https://www.nytimes.com/2020/09/22/world/covid-coronavirus.html")

        var m4 = Result( "By THE NEW YORK TIMES", "Article", "Deaths Surpass 200, and State Department Urges Against Travel to China",
                "2020-01-30", "The New York Times", "https://www.nytimes.com/2020/09/22/world/covid-coronavirus.html")

        return listOf(m1, m2, m3, m4)
    }


}