package com.example.mvvmpr.hilt.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(
    private val noteDAO: NoteDAO
) {
    suspend fun getNote() : Note {
        return withContext(Dispatchers.IO){
            noteDAO.getNote()[0]
        }
    }

    suspend fun saveNote(note : Note) {
        withContext(Dispatchers.IO) {
            noteDAO.insert(note)
        }
    }
}