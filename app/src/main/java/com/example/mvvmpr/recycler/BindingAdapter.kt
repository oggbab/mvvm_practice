package com.example.mvvmpr.recycler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, todoList: List<Todo>?) {
        if (recyclerView.adapter == null) {
            val adapter = TodoRecyclerAdapter()
            recyclerView.adapter = adapter
            recyclerView.stopScroll()
        }

        todoList?.let{
            val myAdapter = recyclerView.adapter as TodoRecyclerAdapter
            myAdapter.submitList(it)
            myAdapter.notifyDataSetChanged()
        }
    }
}