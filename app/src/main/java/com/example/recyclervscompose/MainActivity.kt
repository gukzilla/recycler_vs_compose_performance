package com.example.recyclervscompose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.recyclervscompose.repo.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("MainActivity", "entries count = ${Repository.entries}")

        findViewById<View>(R.id.launchRecyclerActivity).setOnClickListener {
            val intent = Intent(it.context, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.launchComposeList).setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }
}