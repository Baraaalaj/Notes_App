package com.example.notessqlite.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notessqlite.Activitys.DatabaseHolder.NotesDatabaseHolder
import com.example.notessqlite.R
import com.example.notessqlite.Activitys.Model.Note
import com.example.notessqlite.databinding.ActivityAddNoteActiviityBinding
import com.google.android.material.snackbar.Snackbar

class AddNoteActiviity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteActiviityBinding
    lateinit var db: NotesDatabaseHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteActiviityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inslization to class NotesDatabaseHolder
        db = NotesDatabaseHolder(this)

        // call fun Insert Notes
        binding.saveButton.setOnClickListener {

            insertNote()
        }
    }

    // start insert Note
    private fun insertNote() {

        val title = binding.titleEditText.text.toString()
        val content = binding.contentEditText.text.toString()
        val note = Note(0, title, content)
        db.insertNote(note)
        finish()
        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        Log.e("Baraa", "Note Saved")

    }
    // End insertNote
}