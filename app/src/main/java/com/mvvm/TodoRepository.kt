package com.mvvm

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

//db dao 등을 초기화 및 세팅
class TodoRepository(application: Application) {

    private val db = RoomDb.getInstance(application)
    private val dao = db?.todoDao()
    private val todos : LiveData<List<Todo>>? = dao?.getAll()

    fun getAll() : LiveData<List<Todo>>? {
        return todos
    }
    fun insert(todo: Todo) {
        try {
            Runnable { dao?.insert(todo) }.run()
        } catch (e : Exception) {

        }
    }

    fun delete(todo: Todo) {
        try {
            Runnable { dao?.delete(todo) }.run()
        } catch (e : Exception) {

        }
    }

    fun update(todo: Todo) {
        try {
            Runnable { dao?.update(todo) }.run()
        } catch (e : Exception) {

        }
    }

}