package kr.co.lion.paginglv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.co.lion.paginglv3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter()
        with(binding) {
            with(rv) {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            refresh.setOnClickListener {
                // PagingSource의 getRefreshKey
                adapter.refresh()
            }
        }

        lifecycleScope.launch {
            viewModel.items.collect {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            // 현재의 로딩 상태 수집
            adapter.loadStateFlow.collect{
                val isLoadingNext = it.source.append is LoadState.Loading
                binding.loadingNext.isVisible = isLoadingNext

                val isLoadingPrev = it.source.prepend is LoadState.Loading
                binding.loadingPrev.isVisible = isLoadingPrev
            }
        }


    }
}