package com.exsample.android_imperative.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exsample.android_imperative.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {

    }
}