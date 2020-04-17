package com.mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    var title : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    override fun toString(): String {
        return "id: $id title: $title "
    }
}