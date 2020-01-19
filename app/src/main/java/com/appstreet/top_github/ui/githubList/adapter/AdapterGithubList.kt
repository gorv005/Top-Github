package com.appstreet.top_github.ui.githubList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appstreet.top_github.R
import com.appstreet.top_github.interfaces.OnClickItem
import com.appstreet.top_github.model.TopGithubData
import com.appstreet.top_github.utils.image_loader.ImageLoader
import kotlinx.android.synthetic.main.data_item.view.*


class AdapterGithubList(val items: List<TopGithubData>,
                        private val onClickItem: OnClickItem<TopGithubData>,
                        val context: Context) :
    RecyclerView.Adapter<AdapterGithubList.ViewHolder>() {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.data_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvUsername?.text = items?.get(position)?.username
        holder?.txt_Name?.text = items?.get(position)?.name
        ImageLoader.with(context).load(holder?.ivAvatar, items?.get(position)?.avatar)

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        internal var tvUsername: TextView
        internal var txt_Name: TextView
        internal var ivAvatar: ImageView

        init {

             tvUsername = view.txt_username
            txt_Name = view.txt_Name
             ivAvatar = view.avatar_image
            view.setOnClickListener{
                onClickItem.onClick(adapterPosition, items[adapterPosition],ivAvatar, tvUsername,txt_Name)

            }
        }
    }

}


