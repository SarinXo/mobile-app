package com.example.loginapp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.activity.logic.auth.retrofit.api.MrsuApi
import com.example.loginapp.activity.logic.auth.retrofit.dto.AuthRequest
import com.example.loginapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var username : EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private val MRSU_URL : String = "https://p.mrsu.ru"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginButton = findViewById(R.id.login_button)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(MRSU_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mrsuApi = retrofit.create(MrsuApi::class.java)

        loginButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val userToken = mrsuApi.getToken(
                    username = binding.login.text.toString(),
                    password = binding.password.text.toString()
                )

                binding.apply {
                    loginText.text = userToken.tokenType
                }
            }
        }
    }

}