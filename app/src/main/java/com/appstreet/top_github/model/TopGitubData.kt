package com.appstreet.top_github.model

import com.google.gson.annotations.SerializedName

data class TopGitubData(@SerializedName("repo")
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
                        val username: String = "")