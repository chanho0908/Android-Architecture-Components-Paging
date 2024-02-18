package kr.co.lion.paginglv4.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.paginglv4.R
import kr.co.lion.paginglv4.databinding.ActivityMainBinding
import kr.co.lion.paginglv4.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_start)

        binding.inputBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("inputText", binding.inputText.text.toString())
            startActivity(intent)
        }
    }
}