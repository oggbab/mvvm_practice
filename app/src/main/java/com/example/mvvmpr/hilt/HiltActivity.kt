package com.example.mvvmpr.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.mvvmpr.R
import com.example.mvvmpr.hilt.room.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    private val noteViewModel : NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)

        findViewById<Button>(R.id.save_btn).setOnClickListener {
            noteViewModel.saveNote(
                title = findViewById<EditText>(R.id.note_title).text.toString(),
                description = findViewById<EditText>(R.id.note_description).text.toString()
            )
        }

        noteViewModel.note.observe(this, {
            findViewById<EditText>(R.id.note_title).setText(it.title)
            findViewById<EditText>(R.id.note_description).setText(it.description)
        })
    }
}