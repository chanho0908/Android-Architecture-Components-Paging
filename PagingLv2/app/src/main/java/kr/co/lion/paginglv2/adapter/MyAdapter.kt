package kr.co.lion.paginglv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.paginglv2.R
import kr.co.lion.paginglv2.databinding.RvItemBinding
import kr.co.lion.paginglv2.model.GithubResponseItem

class MyAdapter : PagingDataAdapter<GithubResponseItem, MyAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubResponseItem>(){
            override fun areItemsTheSame(
                oldItem: GithubResponseItem,
                newItem: GithubResponseItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GithubResponseItem,
                newItem: GithubResponseItem,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    class MyViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.textArea.text = getItem(position)?.name


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

}