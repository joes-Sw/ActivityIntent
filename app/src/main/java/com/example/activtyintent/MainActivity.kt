package com.example.activtyintent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activtyintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivityLifecycle"
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToSecondActivity.setOnClickListener {
            val username = binding.edtName.text.toString()
            val password = binding.edtPassword.text.toString()
            val email = binding.edtEmail.text.toString()
            val phone = binding.edtPhone.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Simpan data ke SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USERNAME", username)
                editor.putString("PASSWORD", password)
                editor.apply() // Simpan data secara asinkron

                // Berpindah ke SecondActivity
                val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java).apply {
                    putExtra(EXTRA_NAME, username)
                    putExtra(EXTRA_EMAIL, email) // Gunakan nilai yang sesuai
                    putExtra(EXTRA_PHONE, phone) // Gunakan nilai yang sesuai
                }
                startActivity(intentToSecondActivity)
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}