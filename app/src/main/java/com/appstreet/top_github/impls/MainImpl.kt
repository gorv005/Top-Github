package com.appstreet.top_github.impls

import com.appstreet.top_github.interfaces.RESTApi
import com.appstreet.top_github.model.TopGitubData

import com.forcast.demo.repository.MainRepository
import io.reactivex.Single

class MainImpl(private val restApi: RESTApi): MainRepository {

    override fun getGitHubList(): Single<List<TopGitubData>> = restApi.getTopGitHub()



}