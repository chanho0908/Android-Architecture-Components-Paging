package kr.co.lion.paginglv4.rv

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.paginglv4.databinding.GithubItemBinding
import kr.co.lion.paginglv4.model.Items

class GithubAdapter : PagingDataAdapter<Items, GithubAdapter.GithubViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }

        }

    }

    inner class GithubViewHolder(val binding: GithubItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return  GithubViewHolder(GithubItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val binding = holder.binding
        val items = getItem(position)
        with(binding){
            root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(items?.url))
                root.context.startActivity(intent)
            }
            url.text = items?.url
            nameArea.text = items?.name
        }

    }

}