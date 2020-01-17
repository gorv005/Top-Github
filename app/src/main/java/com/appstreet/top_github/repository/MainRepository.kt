package com.forcast.demo.repository
import com.appstreet.top_github.model.TopGitubData
import io.reactivex.Single

interface MainRepository {
    fun getGitHubList(): Single<List<TopGitubData>>
}