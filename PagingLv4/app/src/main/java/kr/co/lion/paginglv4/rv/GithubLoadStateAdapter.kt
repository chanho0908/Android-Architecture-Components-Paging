package kr.co.lion.paginglv4.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import kr.co.lion.paginglv4.databinding.LoadStateItemBinding

class GithubLoadStateAdapter(
    private val retry : () -> Unit
) : LoadStateAdapter<GithubLoadStateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState, ): GithubLoadStateViewHolder {

        val view = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context))
        return GithubLoadStateViewHolder(view, retry)

    }

    override fun onBindViewHolder(holder: GithubLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


}