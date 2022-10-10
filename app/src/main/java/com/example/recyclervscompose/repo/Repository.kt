package com.example.recyclervscompose.repo

import com.example.recyclervscompose.recyclerview.Entry

object Repository {

    val entries = List(1000) {
        Entry("Item $it")
    }
}