package com.appstreet.top_github.interfaces
import com.appstreet.top_github.BuildConfig
import com.appstreet.top_github.model.TopGitubData
import io.reactivex.Single
import retrofit2.http.*

interface RESTApi {

    @GET("developers?language=${BuildConfig.language}&since=${BuildConfig.since}")
    fun getTopGitHub(): Single<List<TopGitubData>>


}