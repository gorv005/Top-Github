package com.appstreet.top_github.ui.githubList

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appstreet.top_github.R
import com.appstreet.top_github.base.BaseActivity
import com.appstreet.top_github.interfaces.OnClickItem
import com.appstreet.top_github.model.TopGithubData
import com.appstreet.top_github.ui.githubDetails.GithubDetailsActivity
import com.appstreet.top_github.ui.githubList.adapter.AdapterGithubList
import com.appstreet.top_github.utils.AndroidUtils
import com.appstreet.top_github.utils.CommonUtil
import com.appstreet.top_github.utils.Logger
import com.appstreet.top_github.utils.UiUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class),
    OnClickItem<TopGithubData> {
    override fun onClick(
        position: Int,
        t: TopGithubData?,
        imageView: ImageView,
        username: TextView,
        name: TextView
    ) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            val imageViewPair = androidx.core.util.Pair.create<View, String>(imageView, getString(R.string.trans_user_image))
            val userNameViewPair =
                androidx.core.util.Pair.create<View, String>(username, getString(R.string.trans_user_name))
            val nameViewPair =
                androidx.core.util.Pair.create<View, String>(username, getString(R.string.trans_name))

            val pairs=ArrayList<androidx.core.util.Pair<View,String>>()
            pairs.add(imageViewPair)
            pairs.add(userNameViewPair)
            pairs.add(nameViewPair)

            val pairArray: Array<androidx.core.util.Pair<View,String>> = pairs.toTypedArray()
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,*pairArray)

            startActivity(
                GithubDetailsActivity.getIntent(this@MainActivity, dataItems?.get(position)),
                options.toBundle()
            )
        }
        else{
            startActivity(
                GithubDetailsActivity.getIntent(this@MainActivity, dataItems?.get(position))
            )
        }
    }


    private var adapterGithubList: AdapterGithubList? = null

    override fun layout(): Int = R.layout.activity_main
    private var dataItems: List<TopGithubData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_github_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        subscribeLoading()
        subscribeUI()
        loadData()
    }


    private fun loadData() {
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
            dataItems = it
            adapterGithubList = AdapterGithubList(it, this@MainActivity, this@MainActivity)

            rv_github_list.adapter = adapterGithubList
        })

    }

    fun showProgressDialog() {

        showProgressDialog(null, AndroidUtils.getString(R.string.please_wait))
    }
}
