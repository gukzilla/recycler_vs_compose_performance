package com.example.recyclervscompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclervscompose.recyclerview.EntryAdapter
import com.example.recyclervscompose.databinding.ActivityRecyclerViewBinding
import com.example.recyclervscompose.repo.Repository

open class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "RecyclerView Sample"
        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = EntryAdapter(Repository.entries)
    }
}
