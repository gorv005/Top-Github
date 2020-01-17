package com.appstreet.top_github.ui.githubList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appstreet.top_github.R
import com.appstreet.top_github.base.BaseActivity
import com.appstreet.top_github.ui.githubList.adapter.AdapterGithubList
import com.appstreet.top_github.utils.AndroidUtils
import com.appstreet.top_github.utils.CommonUtil
import com.appstreet.top_github.utils.Logger
import com.appstreet.top_github.utils.UiUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {

    private var adapterGithubList: AdapterGithubList? = null

    override fun layout(): Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_github_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        subscribeLoading()
        subscribeUI()
        loadData()
    }


    private fun loadData(){
        if (CommonUtil.isInternetAvailable(this)) {
            model.loadingGitHubData()
        }
    }
    private fun subscribeLoading() {

        model.searchEvent.observe(this, Observer {
            if (it.isLoading) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
            it.error?.run {
                UiUtils.showInternetDialog(this@MainActivity, R.string.something_went_wrong)
            }
        })
    }

    private fun subscribeUI() {
        model.githubList.observe(this, Observer {
            Logger.Debug("DEBUG", it.toString())
            adapterGithubList = AdapterGithubList(it,this@MainActivity)
            rv_github_list.adapter = adapterGithubList

        })

    }

    fun showProgressDialog() {

        showProgressDialog(null, AndroidUtils.getString(R.string.please_wait))
    }
}
