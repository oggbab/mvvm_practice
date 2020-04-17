package com.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll() : LiveData<List<Todo>>

    @Insert
    fun insert(todo : Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(todo : Todo)

    @Delete
    fun delete(todo : Todo)
}