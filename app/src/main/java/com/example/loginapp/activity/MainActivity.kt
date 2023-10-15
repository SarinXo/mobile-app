package com.example.loginapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.loginapp.R
import com.example.loginapp.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}