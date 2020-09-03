package com.example.application

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getViewState().observe(this, Observer { value ->

        })
        val countButton = findViewById<Button>(R.id.button)
        val textCounter = findViewById<TextView>(R.id.textView)
        countButton.setOnClickListener {

            viewModel.getViewState().observe(this, Observer { value ->
                textCounter.text = value
            })
        }

    }

}


