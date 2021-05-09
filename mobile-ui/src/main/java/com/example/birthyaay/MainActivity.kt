package com.example.birthyaay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.remote.CheckingHilt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}
