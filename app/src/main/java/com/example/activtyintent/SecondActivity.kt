package com.example.activtyintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activtyintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            // Memeriksa result code
            if (result.resultCode == Activity.RESULT_OK) {
                // Mengambil data Intent
                val data = result.data
                // Mendapatkan alamat dari data Intent
                val email = data?.getStringExtra(MainActivity.EXTRA_EMAIL)
                val phone = data?.getStringExtra(MainActivity.EXTRA_PHONE)
                val name = data?.getStringExtra(EXTRA_NAME)
            }
        }
    companion object{
        const val EXTRA_NAME = "extra_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(EXTRA_NAME)
        val email = intent.getStringExtra(MainActivity.EXTRA_EMAIL)
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)
        with(binding){
            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    .apply { putExtra(MainActivity.EXTRA_EMAIL,email) }
                    .apply { putExtra(MainActivity.EXTRA_PHONE,phone) }
                    .apply { putExtra(EXTRA_NAME,name) }

                launcher.launch(intent)
            }
        }
    }
}