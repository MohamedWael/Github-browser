package io.thed.modules.repos.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.thed.modules.repos.response.RepoItem
import io.thed.modules.repos.widges.GithubRepoView

class GithubReposAdapter :
    PagedListAdapter<RepoItem, GithubReposAdapter.GithubRepoViewHolder>(object :
        DiffUtil.ItemCallback<RepoItem>() {
        override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
            return oldItem.id == newItem.id && oldItem.pushedAt == newItem.pushedAt
        }
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        return GithubRepoViewHolder(GithubRepoView(parent.context))
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        if (getItem(position) != null) {
            (holder.itemView as GithubRepoView).bind(getItem(position)!!)
        }
    }


    class GithubRepoViewHolder(view: GithubRepoView) : RecyclerView.ViewHolder(view)
}