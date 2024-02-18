package kr.co.lion.aac_paging.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.co.lion.aac_paging.R
import kr.co.lion.aac_paging.ViewModel.MainViewModel
import kr.co.lion.aac_paging.adapter.MyAdapter
import kr.co.lion.aac_paging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter()
        with(binding.rv){
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.items.collect{
                adapter.submitData(it)
            }
        }
    }
}