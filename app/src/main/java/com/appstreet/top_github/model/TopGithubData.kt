package com.appstreet.top_github.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopGithubData(@SerializedName("repo")
                        val repo: Repo,
                         @SerializedName("name")
                        val name: String = "",
                         @SerializedName("avatar")
                        val avatar: String = "",
                         @SerializedName("type")
                        val type: String = "",
                         @SerializedName("url")
                        val url: String = "",
                         @SerializedName("username")
                        val username: String = ""): Parcelable

