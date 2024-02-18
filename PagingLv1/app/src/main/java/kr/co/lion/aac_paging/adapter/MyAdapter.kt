package kr.co.lion.aac_paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.aac_paging.R
import kr.co.lion.aac_paging.databinding.ItemViewBinding
import kr.co.lion.aac_paging.model.User

// 데이터를 RecyclerView 목록에 수신하는 어댑터
class MyAdapter: PagingDataAdapter<User, MyAdapter.UserViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class UserViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val binding = holder.binding
        with(binding){
            id.text = getItem(position)?.id.toString()
            userName.text = getItem(position)?.userName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return UserViewHolder(view)
    }
}