package kr.co.lion.paginglv4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import kr.co.lion.paginglv4.rv.GithubAdapter
import kr.co.lion.paginglv4.viewModel.MainViewModel
import kr.co.lion.paginglv4.viewModel.MainViewModelFactory
import kr.co.lion.paginglv4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var githubAdapter: GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val str = intent.getStringExtra("inputText").toString()
        Log.d("MainActivity", str)

        githubAdapter = GithubAdapter()

        loadData(str)

//        with(binding.rv){
//            this.adapter = adapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//
//        lifecycleScope.launch {
//            viewModel.items.collect {
//                adapter.submitData(it)
//            }
//        }
    }

    fun loadData(str : String) {

        viewModelFactory = MainViewModelFactory(str)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = githubAdapter

        lifecycleScope.launch {
            viewModel.items.collect {
                githubAdapter.submitData(it)
            }
        }

    }
}