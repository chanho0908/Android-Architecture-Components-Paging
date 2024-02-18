package kr.co.lion.paginglv3

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.paginglv3.databinding.RvItemBinding
import kr.co.lion.paginglv3.model.Data
import kr.co.lion.paginglv3.newModel.NewItems

class MyAdapter : PagingDataAdapter<NewItems, MyAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewItems>(){

            override fun areItemsTheSame(oldItem: NewItems, newItem: NewItems): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewItems, newItem: NewItems): Boolean {
                return oldItem == newItem
            }

        }
    }


    class MyViewHolder(val binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position)
        binding.textArea.text = item?.id.toString()
        binding.imgArea.setImageURI(Uri.parse(item?.owner?.avatar_url))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

}