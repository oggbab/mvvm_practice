package com.mvvm

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase

//ViewModel 과 Repository 분리
class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TodoRepository(application)
    val todos : LiveData<List<Todo>>? = repository.getAll()
    var newTodo : String = ""
    var checked = false

    fun insert(todo: String) {
        repository.insert(Todo(todo))
    }

    fun update(todo: Todo) {
        repository.update(todo)
    }

    fun delete(todo: Todo) {
        repository.delete(todo)
    }
}