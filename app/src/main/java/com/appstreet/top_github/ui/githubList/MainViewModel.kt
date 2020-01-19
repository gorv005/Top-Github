package com.appstreet.top_github.ui.githubList

import androidx.lifecycle.MutableLiveData
import com.appstreet.top_github.base.AbstractViewModel
import com.appstreet.top_github.interfaces.SchedulerProvider
import com.appstreet.top_github.model.SearchEvent
import com.appstreet.top_github.model.TopGithubData
import com.forcast.demo.repository.MainRepository

class MainViewModel(
    private val mainRepo: MainRepository,
    private val scheduler: SchedulerProvider
) : AbstractViewModel() {

    val githubList = MutableLiveData<List<TopGithubData>>()
    val searchEvent = MutableLiveData<SearchEvent>()


    fun loadingGitHubData() {
        searchEvent.value = SearchEvent(isLoading = true)
        launch {
            mainRepo.getGitHubList()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doAfterTerminate {
                    searchEvent.value = SearchEvent(isLoading = false, isSuccess = true)
                }
                .subscribe({
                    githubList.value = it
                }, {
                    searchEvent.value = SearchEvent(isLoading = false, isSuccess = false)

                })
        }
    }


}