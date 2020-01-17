package com.appstreet.top_github.ui.githubList.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.top_github.R
import com.appstreet.top_github.model.TopGitubData
import kotlinx.android.synthetic.main.data_item.view.*


class AdapterGithubList(val items: List<TopGitubData>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.data_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvUsername?.text = items.get(position).username
        holder?.tvRepositoryName?.text = items.get(position).name

    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvUsername = view.txt_username
    val tvRepositoryName = view.txt_repository_name

}
