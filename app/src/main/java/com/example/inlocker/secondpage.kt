package com.example.inlocker;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.secondpage.*

class secondpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondpage)
        FoodBtn.setOnClickListener {

            startActivity(Intent(this, ProductActivity::class.java))
        }

    }
}