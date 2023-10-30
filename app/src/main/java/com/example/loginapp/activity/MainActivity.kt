package com.example.loginapp.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.loginapp.R
import com.example.loginapp.activity.logic.auth.retrofit.api.MrsuApi
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
                try{
                    val userToken = mrsuApi.getToken(
                        username = binding.login.text.toString(),
                        password = binding.password.text.toString()
                    )

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channelId = "my_channel_id"
                        val channelName = "My Channel"
                        val importance = NotificationManager.IMPORTANCE_DEFAULT
                        val channel = NotificationChannel(channelId, channelName, importance)
                        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.createNotificationChannel(channel)
                    }

                    val notificationId = 1
                    val channelId = "my_channel_id"

                    val notificationBuilder = NotificationCompat.Builder(applicationContext, channelId)
                        .setSmallIcon(R.drawable.circle)
                        .setContentTitle("Success")
                        .setContentText("Вы были успешно залогинены")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(notificationId, notificationBuilder.build())

                }catch (e: Exception){
                    Toast.makeText(
                        this@MainActivity,
                        "Неверный логин или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}