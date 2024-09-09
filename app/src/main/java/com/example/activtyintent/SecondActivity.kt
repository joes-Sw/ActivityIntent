package com.example.activtyintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activtyintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
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
                val username = binding.edtName.text.toString()
                val password = binding.password.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    // Ambil data dari SharedPreferences
                    val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    val storedUsername = sharedPreferences.getString("USERNAME", null)
                    val storedPassword = sharedPreferences.getString("PASSWORD", null)

                    if (username == storedUsername && password == storedPassword) {
                        Toast.makeText(this@SecondActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                        // Pindah ke ThirdActivity
                        val intent = Intent(this@SecondActivity, ThirdActivity::class.java).apply {
                            putExtra(MainActivity.EXTRA_EMAIL, email)
                            putExtra(MainActivity.EXTRA_PHONE, phone)
                            putExtra(EXTRA_NAME, name)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SecondActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SecondActivity, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}