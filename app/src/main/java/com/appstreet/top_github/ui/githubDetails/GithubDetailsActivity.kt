package com.appstreet.top_github.ui.githubDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.appstreet.top_github.R
import com.appstreet.top_github.model.TopGithubData
import com.appstreet.top_github.utils.AndroidUtils
import com.appstreet.top_github.utils.image_loader.ImageLoader
import kotlinx.android.synthetic.main.activity_github_details.*

class GithubDetailsActivity : AppCompatActivity() {


    private lateinit var githubData: TopGithubData

    companion object {
        const val KEY_GIT_DATA = "KEY_GIT_DATA"

        fun getIntent(context: Context, data: TopGithubData?): Intent? {
            return Intent(context, GithubDetailsActivity::class.java)
                .putExtra(KEY_GIT_DATA, data)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_details)
        intent?.run {
            githubData = getParcelableExtra(KEY_GIT_DATA) as TopGithubData

        }
        setData()
    }

    fun setData() {
        githubData.run {
            ImageLoader.with(this@GithubDetailsActivity).load(img_user, githubData?.avatar)


            //Set User Details
            txtUsername.text = AndroidUtils.getString(R.string.username)+": "+githubData?.username
            txtName.text = AndroidUtils.getString(R.string.name)+": "+githubData?.name
            txtGitUrl.text = AndroidUtils.getString(R.string.url)+": "+githubData?.url

            // Set Repo Details
            txtRepoName.text = AndroidUtils.getString(R.string.name)+": "+githubData?.repo?.name
            txtGitRepoUrl.text = AndroidUtils.getString(R.string.url)+": "+githubData?.repo?.url
            txtGitRepoDescription.text = AndroidUtils.getString(R.string.description)+": "+githubData?.repo?.description
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            //to reverse the scene transition animation
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }
}
