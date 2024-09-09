package com.example.activtyintent

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activtyintent.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            // Memeriksa result code
            if (result.resultCode == Activity.RESULT_OK) {
                // Mengambil data Intent
                val data = result.data
                // Mendapatkan alamat dari data Intent
                val name = data?.getStringExtra(SecondActivity.EXTRA_NAME)
                val email = data?.getStringExtra(MainActivity.EXTRA_EMAIL)
                val phone = data?.getStringExtra(MainActivity.EXTRA_PHONE)
                // Menetapkan teks di TextView
                binding.txtName.text = "Welcome $name"
                binding.txtEmail.text = "Your $email has been activated"
                binding.txtPhone.text = "Your $phone has been registered"
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(SecondActivity.EXTRA_NAME)
        val email = intent.getStringExtra(MainActivity.EXTRA_EMAIL)
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)

//        val red = Color.RED

        with(binding) {
            txtName.text = getSpannableString("Welcome ", name)
            txtEmail.text = getSpannableString("Your ", email, " has been activated")
            txtPhone.text = getSpannableString("Your ", phone, " has been registered")
        }

    }
    private fun getSpannableString(prefix: String, text: String?, suffix: String = ""): SpannableString {
        val spannableString = SpannableString("$prefix$text$suffix")
        val start = prefix.length
        val end = start + (text?.length ?: 0)

        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}